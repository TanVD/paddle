package io.paddle.specification.visitor

import io.paddle.specification.tree.*

interface SpecTreeVisitor<R> {
    fun visit(integerNode: IntegerSpecTreeNode): R

    fun visit(booleanNode: BooleanSpecTreeNode): R

    fun visit(stringNode: StringSpecTreeNode): R

    fun <T : ConfigurationSpecification.SpecTreeNode> visit(arrayNode: ArraySpecTreeNode<T>): R

    fun visit(compositeNode: CompositeSpecTreeNode): R
}
