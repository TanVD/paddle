package io.paddle.specification.tree

import io.paddle.specification.visitor.JsonSchemaSpecVisitor
import io.paddle.utils.json.schema.JSONSCHEMA
import io.paddle.utils.splitAndTrim

class JsonSchemaSpecification(root: CompositeSpecTreeNode) : SpecializedConfigSpec<String>(root) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : SpecTreeNode> get(key: String): T? {
        val parts = key.splitAndTrim(".").takeIf { it.isNotEmpty() } ?: return root as T?

        val path = parts.dropLast(1)
        val name = parts.last()

        var current: Map<String, SpecTreeNode> = root.children
        for (part in path) {
            current = (current[part] as? CompositeSpecTreeNode)?.children ?: return null
        }

        return current[name] as? T?
    }

    override fun specialize() = JsonSchemaSpecVisitor.visit(root)

    override fun toString() = specialize()

    companion object {
        private const val schemaUrl = "/schema/paddle-schema.json"

        val base: JsonSchemaSpecification by lazy {
            JsonSchemaSpecification(readSchema())
        }

        @JvmStatic
        private fun readSchema(): CompositeSpecTreeNode {
            val inputStreamWithSchema = Companion::class.java.classLoader.getResourceAsStream(schemaUrl)
            val schema = inputStreamWithSchema?.bufferedReader()?.use {
                it.readText()
            }
            return schema?.let { JSONSCHEMA.parse(it) } ?: CompositeSpecTreeNode()
        }
    }
}
