package specification

import io.paddle.specification.tree.CompositeSpecTreeNode
import io.paddle.specification.tree.ConfigurationSpecification
import io.paddle.specification.visitor.SpecTreeStructureVisitor
import io.paddle.utils.json.schema.JSONSCHEMA
import io.paddle.utils.resource.ResourceUtils
import java.io.File
import kotlin.test.assertIs
import kotlin.test.assertNotNull

internal object TestCommon {
    val schemas = listOf("base-json-schema.json")

    fun readContent(schemaPath: String): String {
        val jsonSchema: File? = ResourceUtils.getResourceFileBy("schemas/$schemaPath")
        assertNotNull(jsonSchema)
        return jsonSchema.readText()
    }

    fun parseAndTypingTreeFrom(schema: String): CompositeSpecTreeNode {
        val content: String = readContent(schema)
        val root: ConfigurationSpecification.SpecTreeNode = JSONSCHEMA.parse(content)
        assertIs<CompositeSpecTreeNode>(root)
        return root
    }

    fun getSpecTreeStructure(root: CompositeSpecTreeNode) = root.accept(SpecTreeStructureVisitor())
}
