package io.paddle.execution

import io.paddle.terminal.Terminal
import io.paddle.terminal.TextOutput
import java.io.File

abstract class CommandExecutor(val configuration: OutputConfiguration) {
    data class OutputConfiguration(val output: TextOutput, val printStdOut: Boolean = true, val printStdErr: Boolean = true)

    abstract fun execute(command: String, args: Iterable<String>, working: File, terminal: Terminal): Int
}