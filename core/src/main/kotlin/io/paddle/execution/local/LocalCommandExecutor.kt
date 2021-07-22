package io.paddle.execution.local

import io.paddle.execution.CommandExecutor
import io.paddle.terminal.Terminal
import io.paddle.terminal.TextOutput
import org.codehaus.plexus.util.cli.*
import java.io.File

class LocalCommandExecutor(output: TextOutput): CommandExecutor(OutputConfiguration(output)) {
    override fun execute(command: String, args: Iterable<String>, working: File, terminal: Terminal): Int {
        return CommandLineUtils.executeCommandLine(
            Commandline().apply {
                workingDirectory = working
                executable = command
                addArguments(args.toList().toTypedArray())
            }, getConsumer(configuration.printStdOut), getConsumer(configuration.printStdErr)
        )
    }

    private fun getConsumer(redirectOutput: Boolean): StreamConsumer {
        if (!redirectOutput) {
            return StreamConsumer { }
        }
        return StreamConsumer { configuration.output.stdout(it + "\n") }
    }
}