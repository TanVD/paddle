package io.paddle.specification.visitor

import io.paddle.specification.tree.*

class SpecTreeStructureVisitor: SpecTreeVisitor<Map<String, Any?>> {
    override fun visit(compositeNode: CompositeSpecTreeNode): Map<String, Any?> {
        return compositeNode.children.mapValues { it.value.accept(this).ifEmpty { null } }
    }

    override fun visit(integerNode: IntegerSpecTreeNode): Map<String, Any?> = emptyMap()

    override fun visit(booleanNode: BooleanSpecTreeNode): Map<String, Any?> = emptyMap()

    override fun visit(stringNode: StringSpecTreeNode): Map<String, Any?> = emptyMap()

    override fun <T : ConfigurationSpecification.SpecTreeNode> visit(arrayNode: ArraySpecTreeNode<T>): Map<String, Any?> = emptyMap()
}
