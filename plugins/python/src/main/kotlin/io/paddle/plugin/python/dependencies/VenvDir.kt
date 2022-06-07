package io.paddle.plugin.python.dependencies

import io.paddle.plugin.python.dependencies.packages.CachedPyPackage.Companion.PYPACKAGE_CACHE_FILENAME
import io.paddle.plugin.python.dependencies.packages.IResolvedPyPackage
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.extensions.interpreter
import io.paddle.plugin.python.utils.RegexCache
import io.paddle.plugin.python.utils.jsonParser
import io.paddle.project.Project
import kotlinx.serialization.decodeFromString
import java.io.File
import java.nio.file.Path

/**
 * A decorator class that contains utilities for accessing python's venv subdirectories.
 *
 * https://docs.python.org/3/library/venv.html#creating-virtual-environments
 */
open class VenvDir(private val directory: File) : File(directory.path) {
    val bin: File
        get() = directory.resolve("bin")

    val sitePackages: File
        get() {
            val libDir = directory.resolve("lib")
            val pythonDir = libDir.listFiles()?.find { it.name.matches(RegexCache.PYTHON_DIR_NAME_REGEX) } ?: error("Incorrect venv structure")
            return pythonDir.resolve("site-packages")
        }

    val pycache: File
        get() = sitePackages.resolve("__pycache__")

    val pyPackageFiles: List<File>
        get() = sitePackages.walkTopDown().asSequence().filter { it.name == PYPACKAGE_CACHE_FILENAME }.toList()

    val pyPackages: List<PyPackage>
        get() = pyPackageFiles.map { jsonParser.decodeFromString(it.readText()) }

    open fun getInterpreterPath(project: Project): Path {
        return bin.resolve(project.interpreter.resolved.version.executableName).toPath()
    }

    fun hasInstalledPackage(pkg: IResolvedPyPackage): Boolean {
        val infoDir = InstalledPackageInfoDir.findByNameAndVersionOrNull(sitePackages, pkg.name, pkg.version)
        return infoDir?.pkg?.repo == pkg.repo // name and version already matched
    }
}
