package io.paddle.utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun File.deleteRecursivelyWithoutSymlinks() {
    if (this.isDirectory && !Files.isSymbolicLink(this.toPath())) {
        val children = this.listFiles() ?: return
        children.forEach { it.deleteRecursivelyWithoutSymlinks() }
    }
    this.delete()
}

fun Path.exists(): Boolean = Files.exists(this)

fun String.toFile(): File = File(this)

fun Collection<File>.absolutePathStrings(): Collection<String> = map(File::getAbsolutePath)
