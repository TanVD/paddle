package io.paddle.utils.json.schema

import io.paddle.specification.tree.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*

internal object JSONSCHEMA {
    private val module = SerializersModule {
        polymorphic(ConfigurationSpecification.SpecTreeNode::class) {
            subclass(CompositeSpecTreeNode::class)
            @Suppress("UNCHECKED_CAST")
            subclass(
                // https://github.com/Kotlin/kotlinx.serialization/issues/944
                ArraySpecTreeNode::class, ArraySpecTreeNode.serializer(
                    PolymorphicSerializer(ConfigurationSpecification.SpecTreeNode::class)
                ) as KSerializer<ArraySpecTreeNode<*>>
            )
            subclass(StringSpecTreeNode::class)
            subclass(IntegerSpecTreeNode::class)
            subclass(BooleanSpecTreeNode::class)
        }
    }

    private val json = Json {
        serializersModule = module
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    inline fun <reified T : Any> string(value: T): String {
        return json.encodeToString(value)
    }

    inline fun <reified T> parse(value: String): T {
        return json.decodeFromString(value)
    }
}
