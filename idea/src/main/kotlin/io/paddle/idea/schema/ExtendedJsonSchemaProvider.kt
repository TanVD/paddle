package io.paddle.idea.schema

import com.intellij.testFramework.LightVirtualFile
import io.paddle.specification.tree.JsonSchemaSpecification
import io.paddle.idea.utils.PaddleProject

class ExtendedJsonSchemaProvider(resource: String, presentable: String, filesToApply: Set<String>) :
    EmbeddedJsonSchemaProvider(resource, presentable, filesToApply) {

    override fun getSchemaFile(): LightVirtualFile? {
        val baseSchema = super.getSchemaFile()
        if (baseSchema != null) {
            val configSpec = PaddleProject.currentProject?.configSpec
            return (configSpec as? JsonSchemaSpecification)?.let { LightVirtualFile(name, it.specialize()) }
        }
        return baseSchema
    }
}
