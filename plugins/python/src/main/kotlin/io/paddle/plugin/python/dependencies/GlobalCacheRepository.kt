package io.paddle.plugin.python.dependencies

import io.paddle.plugin.python.PaddlePyConfig
import io.paddle.plugin.python.dependencies.packages.*
import io.paddle.plugin.python.utils.deepResolve
import io.paddle.plugin.python.utils.exists
import io.paddle.project.Project
import java.io.File
import java.nio.file.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.concurrent.schedule
import kotlin.io.path.deleteExisting
import kotlin.io.path.name

/**
 * A service class for managing Paddle's cache.
 *
 * @see PaddlePyConfig.cacheDir
 */
object GlobalCacheRepository {
    private const val CACHE_SYNC_PERIOD_MS: Long = 60000L
    private val cachedPackages: MutableCollection<CachedPyPackage> = ConcurrentHashMap.newKeySet()

    init {
        Timer("CachedPackagesSynchronizer", true)
            .schedule(delay = 0, period = CACHE_SYNC_PERIOD_MS) { updateCache() }
    }

    fun updateCache() {
        cachedPackages.clear()
        PaddlePyConfig.cacheDir.toFile().listFiles()?.forEach { repoDir ->
            repoDir.listFiles()?.forEach { packageDir ->
                packageDir.listFiles()?.forEach { srcPath ->
                    cachedPackages.add(CachedPyPackage.load(srcPath.toPath()))
                }
            }
        }
    }

    private fun getPathToCachedPackage(pkg: PyPackage): Path =
        PaddlePyConfig.cacheDir.deepResolve(
            pkg.repo.cacheFileName,
            pkg.name,
            pkg.version
        )

    fun findPackage(pkg: PyPackage, project: Project, tempVenvManager: AbstractTempVenvManager = TempVenvManager.getInstance(project)): CachedPyPackage {
        return cachedPackages.find { it.pkg == pkg && it.srcPath.exists() }
            ?: installToCache(pkg, tempVenvManager)
    }

    private fun installToCache(pkg: PyPackage, tempVenvManager: AbstractTempVenvManager): CachedPyPackage {
        return tempVenvManager.install(pkg).expose(
            onSuccess = {
                copyPackageRecursivelyFromTempVenv(pkg, tempVenvManager).also {
                    tempVenvManager.uninstall(pkg)
                }
            },
            onFail = { error("Some conflict occurred during installation of ${pkg.name}.") }
        )
    }

    private fun copyPackageRecursivelyFromTempVenv(pkg: PyPackage, tmpVenvManager: AbstractTempVenvManager): CachedPyPackage {
        val targetPathToCache = getPathToCachedPackage(pkg)

        copyPackageSourcesFromTempVenv(tmpVenvManager, pkg, targetPathToCache)

        val cachedPkg = CachedPyPackage(pkg, srcPath = targetPathToCache)
        cachedPackages.add(cachedPkg)

        return cachedPkg
    }

    private fun copyPackageSourcesFromTempVenv(venvManager: AbstractTempVenvManager, pkg: IResolvedPyPackage, targetPathToCache: Path) {
        val packageSources = venvManager.getFilesRelatedToPackage(pkg)
        val sep = File.separatorChar
        packageSources.forEach {
            val commonPathBin = Paths.get(it.path.commonPrefixWith(venvManager.venv.bin.path).trim(sep))
            val commonPathPyCache = Paths.get(it.path.commonPrefixWith(venvManager.venv.pycache.path).trim(sep))
            val targetPathToFile = when {
                commonPathBin.name == "bin" -> {
                    // If common path prefix ends with "bin", copy it to "BIN" folder
                    val suffix = it.path.substringAfter(venvManager.venv.bin.path).trim(sep)
                    targetPathToCache.resolve("BIN").deepResolve(*suffix.split(sep).toTypedArray()).toFile()
                }
                commonPathPyCache.name == "__pycache__" -> {
                    // If common path prefix ends with "__pycache__", copy it to "PYCACHE" folder
                    val suffix = it.path.substringAfter(venvManager.venv.pycache.path).trim(sep)
                    targetPathToCache.resolve("PYCACHE").deepResolve(*suffix.split(sep).toTypedArray()).toFile()
                }
                else -> {
                    // Otherwise, it is general file from site-packages folder which should be copied as is
                    val suffix = it.path.substringAfter(venvManager.venv.sitePackages.path).trim(sep)
                    targetPathToCache.deepResolve(*suffix.split(sep).toTypedArray()).toFile()
                }
            }
            targetPathToFile.parentFile.mkdirs()
            it.copyRecursively(targetPathToFile, overwrite = true)
        }
    }

    fun createSymlinkToPackage(cachedPkg: CachedPyPackage, venv: VenvDir) {
        cachedPkg.sources.forEach {
            when (it.name) {
                "BIN" -> {
                    it.listFiles()?.forEach { executable ->
                        val link = venv.bin.resolve(executable.name).toPath()
                        if (link.exists()) {
                            link.deleteExisting()
                        }
                        Files.createSymbolicLink(link, executable.toPath())
                    }
                }
                "PYCACHE" -> {
                    it.listFiles()?.forEach { pyc ->
                        val link = venv.pycache.resolve(pyc.name).toPath()
                        if (link.exists()) {
                            link.deleteExisting()
                        }
                        Files.createSymbolicLink(link, pyc.toPath())
                    }
                }
                else -> {
                    val link = venv.sitePackages.resolve(it.name).toPath()
                    if (link.exists()) {
                        link.deleteExisting()
                    }
                    Files.createSymbolicLink(link, it.toPath())
                }
            }
        }
    }
}
