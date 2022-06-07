package io.paddle.idea.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import io.paddle.idea.utils.PaddleProject
import io.paddle.plugin.pyinjector.extensions.pyPluginsRepositories
import io.paddle.plugin.repositories.jarPluginsRepositories
import io.paddle.project.Project

class LocalPluginsNamesProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        val prefix = result.prefixMatcher.prefix
        val project: Project = PaddleProject.currentProject!!

        val jarVariants = project.jarPluginsRepositories.findAvailablePluginsBy(prefix)
        for ((pluginName, repos) in jarVariants) {
            repos.forEach {
                result.addElement(LookupElementBuilder.create(pluginName).withTypeText(it.name, true))
            }
        }

        val pyModuleVariants = project.pyPluginsRepositories.withPyModules.findAvailablePluginsBy(prefix)
        for ((pluginName, repos) in pyModuleVariants) {
            repos.forEach {
                result.addElement(LookupElementBuilder.create(pluginName).withTypeText(it.name, true))
            }
        }
    }
}
