package io.paddle.plugin.python.dependencies.resolvers

import io.paddle.execution.ExecutionResult
import io.paddle.plugin.python.PaddlePyConfig
import io.paddle.plugin.python.dependencies.index.PyPackageRepositoryIndexer
import io.paddle.plugin.python.dependencies.index.distributions.ArchivePyDistributionInfo
import io.paddle.plugin.python.dependencies.index.distributions.WheelPyDistributionInfo
import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepositories
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepository
import io.paddle.plugin.python.extensions.*
import io.paddle.plugin.python.utils.*
import io.paddle.project.Project
import io.paddle.tasks.Task
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.*
import org.codehaus.plexus.util.cli.CommandLineUtils
import org.codehaus.plexus.util.cli.Commandline
import java.nio.file.Path
import kotlin.io.path.absolutePathString

object PipResolver {
    const val PIP_RESOLVER_URL = "https://github.com/SmirnovOleg/pip/releases/download/22.1.dev0-beta/pip_resolver-22.1.dev0-py3-none-any.whl"
    private const val PACKAGE_PROPERTIES_NUM = 7

    private val SATISFIED_REQUIREMENT_REGEX = Regex(
        "^Requirement already satisfied: " +
            "(?<name>.*?)" +
            "(==|>=|<=|>|<|~=|===)" +
            ".* (\\(from (?<comesFromName>.*?) (?<comesFromVersion>[\\w\\-_.*+!]+?)\\))? " +
            "\\((?<version>[\\w\\-_.*+!]+?)\\)"
    )

    fun resolve(project: Project, requirements: List<Requirements.Descriptor> = project.requirements.descriptors,
                repositories: PyPackageRepositories = project.repositories.resolved,
                interpreterPath: Path = project.environment.localInterpreterPath): Set<PyPackage> {
        // Collect requirements which repo is not specified directly (or specified as PyPi)
        val generalRequirements = requirements
            .filter { it.repo == null || it.repo == PyPackageRepository.PYPI_REPOSITORY.name }
            .map { it.name + (it.version?.let { v -> "==$v" } ?: "") }

        if (generalRequirements.isEmpty()) {
            return emptySet()
        }

        val pipResolveArgs = listOf("-m", "pip", "resolve") + generalRequirements + repositories.asPipArgs

        val output = ArrayList<String>()
        Cache.find(pipResolveArgs)?.let { output.addAll(it) }
            ?: ExecutionResult(
                CommandLineUtils.executeCommandLine(
                    Commandline().apply {
                        executable = interpreterPath.absolutePathString()
                        addArguments(pipResolveArgs.toTypedArray())
                    },
                    { output.add(it); project.terminal.stdout(it) },
                    { project.terminal.stderr(it) }
                )
            )

        // TODO: resolve requirements which repo is specified directly

        Cache.update(pipResolveArgs, output)
        return parse(output, project, repositories)
    }

    private fun parse(output: List<String>, project: Project, repositories: PyPackageRepositories): Set<PyPackage> {
        val startIdx = output.indexOfFirst { it == "--- RESOLVED-BEGIN ---" }
        val endIdx = output.indexOfLast { it == "--- RESOLVED-END ---" }
        if (startIdx == -1 || endIdx == -1) {
            output.map { project.terminal.stderr(it) }
            throw Task.ActException("Package resolution failed.")
        }

        val lines = output.slice((startIdx + 1) until endIdx)
        val comesFromByPackage = HashMap<PyPackage, PyPackageUrl>()

        for (i in lines.indices step PACKAGE_PROPERTIES_NUM) {
            val name = lines[i].substringAfter(": ")
            // val constraints = lines[i + 1].substringAfter(": ")
            // val fileExtension = lines[i + 2].substringAfter(": ")
            val filename = lines[i + 3].substringAfter(": ")
            val repoUrl = lines[i + 4].substringAfter(": ").substringBeforeLast("/simple/")
            var distributionUrl = lines[i + 5].substringAfter(": ")
            val comesFromDistributionUrl = lines[i + 6].substringAfter(": ")

            val pyDistributionInfo = WheelPyDistributionInfo.fromString(filename)
                ?: ArchivePyDistributionInfo.fromString(filename)
                ?: error("FIXME: Unknown distribution type: $filename")

            val repo = if (repoUrl == "None") {
                runBlocking {
                    PyPackageRepository.PYPI_REPOSITORY.also {
                        distributionUrl = PyPackageRepositoryIndexer.getDistributionUrl(pyDistributionInfo, it)
                            ?: error(
                                "Distribution $filename was not found in the repository ${it.url}.\n" +
                                    "It is possible that it was resolved from your local cache, " +
                                    "which is deprecated since it is not available online anymore.\n" +
                                    "Please, consider removing $distributionUrl and re-running the task."
                            )
                    }
                }
            } else {
                repositories.all.find { it.url.trimmedEquals(repoUrl) }
                    ?: throw IllegalStateException("Unknown repository: $repoUrl")
            }

            val pkg = PyPackage(name, pyDistributionInfo.version, repo, distributionUrl)
            comesFromByPackage[pkg] = comesFromDistributionUrl
        }

        // Restoring inter-package dependencies via 'comesFrom' field
        for ((pkg, comesFromDistributionUrl) in comesFromByPackage) {
            if (comesFromDistributionUrl != "None") {
                val comesFrom = comesFromByPackage.keys.find { it.distributionUrl == comesFromDistributionUrl }
                pkg.comesFrom = comesFrom
            }
        }

        return comesFromByPackage.keys
    }

    object Cache {
        private val storage = PaddlePyConfig.pipResolverCachePath.toFile()

        private var cache: Map<String, List<String>>
            get() {
                storage.parentFile.mkdirs()
                return storage.takeIf { it.exists() }
                    ?.let {
                        jsonParser.decodeFromString(
                            MapSerializer(String.serializer(), ListSerializer(String.serializer())),
                            it.readText()
                        )
                    }
                    ?: emptyMap()
            }
            set(value) {
                storage.parentFile.mkdirs()
                storage.writeText(
                    jsonParser.encodeToString(
                        MapSerializer(String.serializer(), ListSerializer(String.serializer())),
                        value
                    )
                )
            }

        fun find(pipResolveArgs: List<String>) = cache[pipResolveArgs.toString()]

        @Synchronized
        fun update(pipResolveArgs: List<String>, output: List<String>) {
            cache = cache.toMutableMap().also { it[pipResolveArgs.toString()] = output }
        }
    }
}
