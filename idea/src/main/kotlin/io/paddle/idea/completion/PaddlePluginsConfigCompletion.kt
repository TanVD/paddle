package io.paddle.idea.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import io.paddle.plugin.pyinjector.extensions.pyPluginsRepositories
import io.paddle.plugin.python.dependencies.repositories.PyPackageRepositories
import io.paddle.project.Project
import org.jetbrains.yaml.psi.YAMLDocument

class PaddlePluginsConfigCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, LOCALS_NAMES, LocalPluginsNamesProvider())
        extend(CompletionType.BASIC, PY_PACKAGE_NAMES, PyPackagePluginsNamesProvider())
        extend(CompletionType.BASIC, PY_PACKAGE_VERSIONS, PyPackagePluginsVersionsProvider())
    }

    companion object {
        private val COMMON_PSI_PREFIX: PsiElementPattern.Capture<PsiElement> = psiElement()
            .inFile(PlatformPatterns.psiFile().withName(PlatformPatterns.string().equalTo("paddle.yaml")))
            .withSuperParent(8, psiElement().withText(PlatformPatterns.string().startsWith("enabled")))
            .withSuperParent(10, psiElement().withText(PlatformPatterns.string().startsWith("plugins")))
            .withSuperParent(12, YAMLDocument::class.java)

        private val PY_PACKAGE_NAMES: PsiElementPattern.Capture<PsiElement> = COMMON_PSI_PREFIX
            .withSuperParent(2, psiElement().withText(PlatformPatterns.string().startsWith("name")))
            .withSuperParent(6, psiElement().withText(PlatformPatterns.string().startsWith("py")))

        private val PY_PACKAGE_VERSIONS: PsiElementPattern.Capture<PsiElement> = COMMON_PSI_PREFIX
            .withSuperParent(2, psiElement().withText(PlatformPatterns.string().startsWith("version")))
            .withSuperParent(6, psiElement().withText(PlatformPatterns.string().startsWith("py")))

        private val LOCALS_NAMES: PsiElementPattern.Capture<PsiElement> = COMMON_PSI_PREFIX
            .withSuperParent(2, psiElement().withText(PlatformPatterns.string().startsWith("name")))
            .withSuperParent(6, psiElement().withText(PlatformPatterns.string().startsWith("local")))
    }
}

class PyPackagePluginsNamesProvider : AbstractPyPackageNameCompletionProvider() {
    override fun repositories(project: Project): PyPackageRepositories = project.pyPluginsRepositories.withPyPackages
}

class PyPackagePluginsVersionsProvider : AbstractPyPackageVersionCompletionProvider() {
    override fun repositories(project: Project): PyPackageRepositories = project.pyPluginsRepositories.withPyPackages
}
