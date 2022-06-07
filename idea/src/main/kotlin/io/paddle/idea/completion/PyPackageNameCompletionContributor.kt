package io.paddle.idea.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.util.ProcessingContext
import io.paddle.idea.utils.PaddleProject
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepositories
import io.paddle.plugin.python.extensions.repositories
import io.paddle.project.Project
import org.jetbrains.yaml.psi.YAMLDocument

class PyPackageNameCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            psiElement()
                .inFile(psiFile().withName(string().equalTo("paddle.yaml")))
                .withSuperParent(2, psiElement().withText(string().startsWith("name:")))
                .withSuperParent(6, psiElement().withText(string().startsWith("libraries:")))
                .withSuperParent(8, psiElement().withText(string().startsWith("requirements:")))
                .withSuperParent(10, YAMLDocument::class.java),
            PyPackageNameCompletionProvider()
        )
    }
}

abstract class AbstractPyPackageNameCompletionProvider : CompletionProvider<CompletionParameters>() {

    abstract fun repositories(project: Project): PyPackageRepositories

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        val prefix = result.prefixMatcher.prefix
        val variants = repositories(PaddleProject.currentProject!!).findAvailablePackagesByPrefix(prefix)

        for ((pkgName, repo) in variants) {
            result.addElement(LookupElementBuilder.create(pkgName).withTypeText(repo.name, true))
        }
    }
}

class PyPackageNameCompletionProvider : AbstractPyPackageNameCompletionProvider() {
    override fun repositories(project: Project): PyPackageRepositories = project.repositories.resolved
}
