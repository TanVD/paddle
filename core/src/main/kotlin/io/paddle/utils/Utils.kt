package io.paddle.utils

fun String.splitAndTrim(vararg delimiters: String): List<String> {
    return this.split(*delimiters).map { it.trim() }.filter { it.isNotBlank() }
}

fun <T, R> List<T>.mapToMutable(transform: T.() -> R): MutableList<R>? {
    return if (isNotEmpty()) mapTo(mutableListOf()) { it.transform() } else null
}
