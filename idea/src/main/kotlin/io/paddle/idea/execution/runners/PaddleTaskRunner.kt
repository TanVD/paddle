package io.paddle.idea.execution.runners

import com.intellij.build.BuildView
import com.intellij.execution.ExecutionManager
import com.intellij.execution.configurations.*
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.*
import com.intellij.execution.testframework.HistoryTestRunnableState
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.openapi.externalSystem.service.execution.ExternalSystemRunnableState
import com.intellij.openapi.externalSystem.util.ExternalSystemConstants
import com.jetbrains.python.run.PythonCommandLineState
import io.paddle.idea.execution.PaddleRunConfiguration
import org.jetbrains.concurrency.resolvedPromise

class PaddleTaskRunner : ProgramRunner<RunnerSettings> {
    override fun getRunnerId(): String = ExternalSystemConstants.RUNNER_ID

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        return profile is PaddleRunConfiguration && DefaultRunExecutor.EXECUTOR_ID == executorId
    }

    override fun execute(environment: ExecutionEnvironment) {
        val state = environment.state ?: return
        ExecutionManager.getInstance(environment.project).startRunProfile(environment) {
            resolvedPromise(doExecute(state, environment))
        }
    }

    private fun doExecute(state: RunProfileState, environment: ExecutionEnvironment): RunContentDescriptor? {
        val executionResult = state.execute(environment.executor, this) ?: return null
        val runContentDescriptor = RunContentBuilder(executionResult, environment).showRunContent(environment.contentToReuse) ?: return null

        if (state is HistoryTestRunnableState || state is PythonCommandLineState) {
            return runContentDescriptor
        }

        (state as ExternalSystemRunnableState).setContentDescriptor(runContentDescriptor)
        if (executionResult.executionConsole is BuildView) {
            return runContentDescriptor
        }

        val descriptor = object : RunContentDescriptor(
            runContentDescriptor.executionConsole, runContentDescriptor.processHandler,
            runContentDescriptor.component, runContentDescriptor.displayName,
            runContentDescriptor.icon, null,
            runContentDescriptor.restartActions
        ) {
            override fun isHiddenContent(): Boolean = true
        }
        descriptor.runnerLayoutUi = runContentDescriptor.runnerLayoutUi
        return descriptor
    }
}
