package io.paddle.specification.tree

import io.paddle.specification.tree.ConfigurationSpecification.SpecTreeNode
import io.paddle.specification.visitor.SpecTreeVisitor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("object")
data class CompositeSpecTreeNode(
    override val title: String? = null,
    override val description: String? = null,

    @SerialName("required")
    var namesOfRequired: MutableList<String>? = null,

    @SerialName("properties")
    val children: MutableMap<String, SpecTreeNode> = hashMapOf(),

    @SerialName("anyOf")
    var validSpecs: MutableList<CompositeSpecTreeNode>? = null
) : SpecTreeNode() {
    override fun <R> accept(visitor: SpecTreeVisitor<R>) = visitor.visit(this)
}

@Serializable
@SerialName("array")
data class ArraySpecTreeNode<T : SpecTreeNode>(override val title: String? = null, override val description: String? = null, val items: T) : SpecTreeNode() {
    override fun <R> accept(visitor: SpecTreeVisitor<R>) = visitor.visit(this)
}

@Serializable
@SerialName("string")
data class StringSpecTreeNode(
    override var title: String? = null,
    override var description: String? = null,

    @SerialName("enum")
    var validValues: MutableList<String>? = null
) : SpecTreeNode() {
    override fun <R> accept(visitor: SpecTreeVisitor<R>) = visitor.visit(this)
}

@Serializable
@SerialName("boolean")
data class BooleanSpecTreeNode(
    override var title: String? = null,
    override var description: String? = null,

    @SerialName("enum")
    var validValues: MutableList<Boolean>? = null
) :
    SpecTreeNode() {
    override fun <R> accept(visitor: SpecTreeVisitor<R>) = visitor.visit(this)
}

@Serializable
@SerialName("number")
data class IntegerSpecTreeNode(
    override var title: String? = null,
    override var description: String? = null,

    @SerialName("enum")
    var validValues: MutableList<Int>? = null
) :
    SpecTreeNode() {
    override fun <R> accept(visitor: SpecTreeVisitor<R>) = visitor.visit(this)
}
