package io.paddle.idea.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import io.paddle.idea.utils.PaddleProject
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepositories
import io.paddle.plugin.python.extensions.repositories
import io.paddle.project.Project
import org.jetbrains.yaml.psi.YAMLDocument

class PyPackageVersionCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement()
                .inFile(PlatformPatterns.psiFile().withName(PlatformPatterns.string().equalTo("paddle.yaml")))
                .withSuperParent(2, PlatformPatterns.psiElement().withText(PlatformPatterns.string().startsWith("version:")))
                .withSuperParent(6, PlatformPatterns.psiElement().withText(PlatformPatterns.string().startsWith("libraries:")))
                .withSuperParent(8, PlatformPatterns.psiElement().withText(PlatformPatterns.string().startsWith("requirements:")))
                .withSuperParent(10, YAMLDocument::class.java),
            PyPackageVersionCompletionProvider()
        )
    }
}

abstract class AbstractPyPackageVersionCompletionProvider : CompletionProvider<CompletionParameters>() {
    abstract fun repositories(project: Project): PyPackageRepositories

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        val prefix = parameters.position.text.trim().removeSuffix(CompletionUtilCore.DUMMY_IDENTIFIER_TRIMMED)
        val packageName = parameters.originalPosition?.parent?.parent?.prevSibling?.prevSibling?.prevSibling?.lastChild?.text ?: return
        val variants = repositories(PaddleProject.currentProject!!).findAvailableDistributionsByPackageName(packageName)

        for ((distribution, repo) in variants) {
            if (!distribution.version.startsWith(prefix)) continue
            result.addElement(
                LookupElementBuilder.create(distribution.version)
                    .withTailText("  ${distribution.ext}", true)
                    .withTypeText(repo.name, true)
            )
        }
    }
}

class PyPackageVersionCompletionProvider : AbstractPyPackageVersionCompletionProvider() {
    override fun repositories(project: Project): PyPackageRepositories = project.repositories.resolved
}
