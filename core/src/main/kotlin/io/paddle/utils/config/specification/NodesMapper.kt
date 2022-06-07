package io.paddle.utils.config.specification

import io.paddle.plugin.interop.*
import io.paddle.specification.tree.*
import io.paddle.specification.visitor.ProtobufMessageVisitor
import io.paddle.utils.mapToMutable

internal object NodesMapper {
    fun toProtobufMessage(root: CompositeSpecTreeNode): CompositeSpecNode = ProtobufMessageVisitor.visit(root).composite

    fun toConfigSpec(node: SpecNode): ConfigurationSpecification.SpecTreeNode =
        when (node.actualCase) {
            SpecNode.ActualCase.COMPOSITE -> node.composite.toConfigSpec()
            SpecNode.ActualCase.ARRAY -> node.array.toConfigSpec()
            SpecNode.ActualCase.STR -> node.str.toConfigSpec()
            SpecNode.ActualCase.BOOLEAN -> node.boolean.toConfigSpec()
            SpecNode.ActualCase.INTEGER -> node.integer.toConfigSpec()
            else -> error("Invalid configuration specification: some node is not set")
        }

    private fun CompositeSpecNode.toConfigSpec(): CompositeSpecTreeNode {
        return CompositeSpecTreeNode(
            title = title.ifBlank { null },
            description = description.ifBlank { null },
            namesOfRequired = requiredList.mapToMutable { this },
            validSpecs = validList.mapToMutable { toConfigSpec() }
        ).also { node ->
            node.children.putAll(propertiesMap.mapValues { toConfigSpec(it.value) })
        }
    }

    private fun ArraySpecNode.toConfigSpec(): ArraySpecTreeNode<*> =
        ArraySpecTreeNode(
            title = title.ifBlank { null },
            description = description.ifBlank { null },
            items = toConfigSpec(items)
        )

    private fun StringSpecNode.toConfigSpec(): StringSpecTreeNode =
        StringSpecTreeNode(
            title = title.ifBlank { null },
            description = description.ifBlank { null },
            validValues = validList.mapToMutable { this }
        )

    private fun IntegerSpecNode.toConfigSpec(): IntegerSpecTreeNode =
        IntegerSpecTreeNode(
            title = title.ifBlank { null },
            description = description.ifBlank { null },
            validValues = validList.mapToMutable { this }
        )

    private fun BooleanSpecNode.toConfigSpec(): BooleanSpecTreeNode =
        BooleanSpecTreeNode(
            title = title.ifBlank { null },
            description = description.ifBlank { null },
            validValues = validList.mapToMutable { this }
        )
}
