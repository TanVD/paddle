package io.paddle.plugin.python.dependencies.lock.models

import io.paddle.plugin.python.dependencies.packages.PyPackage
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepository
import io.paddle.plugin.python.dependencies.resolvers.PyDistributionsResolver
import io.paddle.plugin.python.utils.PyPackageUrl
import io.paddle.project.PaddleProject
import io.paddle.tasks.Task
import kotlinx.serialization.Serializable

@Serializable
data class LockedPyPackageIdentifier(
    val name: String,
    val version: String,
    val repoMetadata: PyPackageRepository.Metadata,
) {
    constructor(pkg: PyPackage) : this(pkg.name, pkg.version, pkg.repo.metadata)

    suspend fun resolveConcreteDistribution(repo: PyPackageRepository, project: PaddleProject): PyPackageUrl {
        return PyDistributionsResolver.resolve(name, version, repo, project)
            ?.substringBefore("#") // drop hash since hashes are compared separately later
            ?: throw Task.ActException("Could not resolve '$name' $version within specified repo: $repoMetadata")
    }
}
