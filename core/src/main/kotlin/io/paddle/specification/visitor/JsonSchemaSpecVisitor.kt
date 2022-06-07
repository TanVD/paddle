package io.paddle.specification.visitor

import io.paddle.specification.tree.*
import io.paddle.utils.json.schema.JSONSCHEMA

object JsonSchemaSpecVisitor : SpecTreeVisitor<String> {
    override fun visit(integerNode: IntegerSpecTreeNode) = JSONSCHEMA.string(integerNode)

    override fun visit(booleanNode: BooleanSpecTreeNode) = JSONSCHEMA.string(booleanNode)

    override fun visit(stringNode: StringSpecTreeNode) = JSONSCHEMA.string(stringNode)

    override fun <T : ConfigurationSpecification.SpecTreeNode> visit(arrayNode: ArraySpecTreeNode<T>) = JSONSCHEMA.string(arrayNode)

    override fun visit(compositeNode: CompositeSpecTreeNode) = JSONSCHEMA.string(compositeNode)
}
