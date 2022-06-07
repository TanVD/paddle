package io.paddle.specification.visitor

import io.paddle.plugin.interop.*
import io.paddle.specification.tree.*

internal object ProtobufMessageVisitor : SpecTreeVisitor<SpecNode> {
    override fun visit(integerNode: IntegerSpecTreeNode): SpecNode =
        specNode {
            integer = integerSpecNode {
                integerNode.title?.let {
                    title = it
                }
                integerNode.description?.let {
                    description = it
                }
                integerNode.validValues?.let {
                    valid.addAll(it)
                }
            }
        }

    override fun visit(booleanNode: BooleanSpecTreeNode): SpecNode =
        specNode {
            boolean = booleanSpecNode {
                booleanNode.title?.let {
                    title = it
                }
                booleanNode.description?.let {
                    description = it
                }
                booleanNode.validValues?.let {
                    valid.addAll(it)
                }
            }
        }

    override fun visit(stringNode: StringSpecTreeNode): SpecNode =
        specNode {
            str = stringSpecNode {
                stringNode.title?.let {
                    title = it
                }
                stringNode.description?.let {
                    description = it
                }
                stringNode.validValues?.let {
                    valid.addAll(it)
                }
            }
        }

    private fun genericVisit(node: ConfigurationSpecification.SpecTreeNode): SpecNode =
        when (node) {
            is CompositeSpecTreeNode -> visit(node)
            is StringSpecTreeNode -> visit(node)
            is IntegerSpecTreeNode -> visit(node)
            is BooleanSpecTreeNode -> visit(node)
            is ArraySpecTreeNode<*> -> visit(node)
        }

    override fun <T : ConfigurationSpecification.SpecTreeNode> visit(arrayNode: ArraySpecTreeNode<T>): SpecNode =
        specNode {
            array = arraySpecNode {
                arrayNode.title?.let {
                    title = it
                }
                arrayNode.description?.let {
                    description = it
                }
                items = genericVisit(arrayNode.items)
            }
        }

    override fun visit(compositeNode: CompositeSpecTreeNode): SpecNode =
        specNode {
            composite = compositeSpecNode {
                compositeNode.title?.also {
                    title = it
                }
                compositeNode.description?.let {
                    description = it
                }
                compositeNode.namesOfRequired?.let {
                    required.addAll(it)
                }
                compositeNode.validSpecs?.let {
                    valid.addAll(it.map { node -> visit(node).composite })
                }
                properties.putAll(compositeNode.children.mapValues { genericVisit(it.value) })
            }
        }
}
