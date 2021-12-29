package io.paddle.plugin.python.dependencies.index

import io.paddle.plugin.python.dependencies.index.distributions.*
import io.paddle.plugin.python.extensions.*
import io.paddle.plugin.python.utils.*
import io.paddle.project.Project
import org.codehaus.plexus.util.Os


object PyDistributionsResolver {
    val osFamily by lazy {
        when {
            Os.isFamily(Os.FAMILY_WINDOWS) -> "win"
            Os.isFamily(Os.FAMILY_MAC) -> "mac"
            Os.isFamily(Os.FAMILY_UNIX) -> "linux"
            else -> error("Unknown OS family.")
        }
    }

    // FIXME: os.arch is architecture of current JRE, not the platform itself?
    //
    val osArch by lazy {
        val arch = Os.OS_ARCH
        when {
            "86" in arch && "64" in arch -> "x86_64"
            "64" in arch && ("arm" in arch || "aarch" in arch) -> "arm64"
            "64" in arch && "amd" in arch -> "amd64"
            "32" in arch -> "32"
            "86" in arch -> "86"
            else -> error("Unknown OS architecture.")
        }
    }

    // See https://www.python.org/dev/peps/pep-0425/#id1
    // https://docs.python.org/3/distutils/apiref.html#distutils.util.get_platform
    suspend fun resolve(name: PyPackageName, version: PyPackageVersion, repository: PyPackagesRepository, project: Project): PyPackageUrl? {
        val distributions = PyPackagesRepositoryIndexer.downloadDistributionsList(name, repository)
            .filter { it.version == version }
        val wheels = distributions.filterIsInstance<WheelPyDistributionInfo>()

        // Building candidates for current Python interpreter
        val pyTags = project.environment.interpreter.version.pep425candidates

        // Building candidates for ABI tag: add "d", "m", or "u" suffix (flag) to each interpreter
        val abiTags = pyTags.map { listOf(it, it + "m", it + "d", it + "u") }.flatten() + listOf("none", "abi3")

        // Intersecting the sets of available platforms with current platform
        val platformTags = wheels.map { it.platformTag }.toSet()
        platformTags.asSequence()
            .map { it.split(".") }.flatten() // splitting compressed tags
            .filter { osFamily in it }
            .filter { osArch in it || "universal" in it || "86" in osArch && "intel" in it }
            .toList() + "any"

        data class Candidate(
            val pyTagRank: Int = Int.MAX_VALUE,
            val abiTagRank: Int = Int.MAX_VALUE,
            val platformTagRank: Int = Int.MAX_VALUE,
            val wheel: PyDistributionInfo? = null
        )
        var bestCandidate = Candidate()
        for (wheel in wheels) {
            val pyTagRank = wheel.requiresPython.split(".").minOfOrNull { pyTags.indexOf(it) } ?: Int.MAX_VALUE
            val abiTagRank = wheel.abiTag.split(".").minOfOrNull { abiTags.indexOf(it) } ?: Int.MAX_VALUE
            val platformTagRank = wheel.platformTag.split(".").minOfOrNull { platformTags.indexOf(it) } ?: Int.MAX_VALUE
            val currentCandidate = Candidate(pyTagRank, abiTagRank, platformTagRank, wheel)

            if (pyTagRank < bestCandidate.pyTagRank) {
                bestCandidate = currentCandidate
            } else if (abiTagRank < bestCandidate.abiTagRank) {
                bestCandidate = currentCandidate
            } else if (platformTagRank < bestCandidate.platformTagRank) {
                bestCandidate = currentCandidate
            }
        }

        val matchedDistributionInfo = bestCandidate.wheel
            ?: distributions.filterIsInstance<ArchivePyDistributionInfo>().firstOrNull()
            ?: return null

        return PyPackagesRepositoryIndexer.getDistributionUrl(matchedDistributionInfo, repository)
    }

    suspend fun resolve(descriptor: Requirements.Descriptor, project: Project): Pair<PyPackageUrl, PyPackagesRepository> {
        val (name, version, _) = descriptor
        val repos = project.repositories.resolved
        val primaryUrl = resolve(name, version, repos.primarySource, project)
        if (primaryUrl != null)
            return primaryUrl to repos.primarySource
        for (repo in repos.extraSources) {
            val extraUrl = resolve(name, version, repo, project)
            if (extraUrl != null) {
                return extraUrl to repo
            }
        }
        error("Could not resolve $name:$version within specified set of repositories.")
    }
}