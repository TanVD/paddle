package io.paddle.specification

import io.paddle.specification.tree.ConfigurationSpecification

open class ConfigSpecView(private val prefix: String, private val inner: ConfigurationSpecification) : ConfigurationSpecification() {
    override fun <T : SpecTreeNode> get(key: String): T? {
        return inner.get("$prefix.$key")
    }
}
