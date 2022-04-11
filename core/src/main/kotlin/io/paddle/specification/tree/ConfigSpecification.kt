package io.paddle.specification.tree

import io.paddle.specification.visitor.SpecTreeVisitor
import kotlinx.serialization.Serializable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ConfigurationSpecification {
    @Suppress("UNCHECKED_CAST")
    class ConfigSpecDelegate<T : SpecTreeNode>(private val name: String, private val default: T? = null) : ReadOnlyProperty<ConfigurationSpecification, T> {
        override fun getValue(thisRef: ConfigurationSpecification, property: KProperty<*>): T {
            return (thisRef.get<T>(name) ?: default) as T
        }
    }

    fun bool(name: String, default: BooleanSpecTreeNode? = null) = ConfigSpecDelegate(name, default)
    fun integer(name: String, default: IntegerSpecTreeNode? = null) = ConfigSpecDelegate(name, default)
    fun string(name: String, default: StringSpecTreeNode? = null) = ConfigSpecDelegate(name, default)
    fun <T : SpecTreeNode> list(name: String, default: ArraySpecTreeNode<T>? = null) = ConfigSpecDelegate(name, default)

    abstract fun <T : SpecTreeNode> get(key: String): T?

    @Serializable
    abstract class SpecTreeNode {
        abstract val title: String?
        abstract val description: String?

        abstract fun <R, D> accept(visitor: SpecTreeVisitor<R, D>, ctx: D): R
    }
}

abstract class SpecializedConfigSpec<R, D> : ConfigurationSpecification() {
    abstract val root: CompositeSpecTreeNode

    abstract val visitor: SpecTreeVisitor<R, D>

    abstract fun specialize(): R

    companion object {
        fun fromResource(configSpecUrl: String): SpecializedConfigSpec<String, Unit> = JsonSchemaSpecification(configSpecUrl)
    }
}