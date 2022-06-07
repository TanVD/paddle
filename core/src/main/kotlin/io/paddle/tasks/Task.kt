package io.paddle.tasks

import io.paddle.project.Project
import io.paddle.terminal.CommandOutput
import io.paddle.utils.tasks.TaskDefaultGroups
import kotlinx.coroutines.*
import java.util.*

abstract class Task(val project: Project) {
    /**
     * Identifier of the task in the project.
     *
     * Note that this identifier will be used to call the task from the terminal.
     */
    abstract val id: String

    /**
     * Tasks are grouped into categories by semantics.
     *
     * Use [TaskDefaultGroups] for standard groups for tasks
     */
    abstract val group: String

    /**
     * Dependencies that should be called before this specific task.
     *
     * Note, that dependencies will be called via [run] method that may use
     * cached results
     */
    open val dependencies: List<Task> = emptyList()

    /**
     * Asserts that dependencies graph of the task is acyclic before execution of dependent tasks.
     *
     * Note, that the property is lazy delegated, so computed only once.
     */
    protected val ensureAcyclicDeps: Unit by lazy {
        val colors = HashMap<Task, Boolean>()
        dfs(this, { colors[it] = false }, { colors[it] = true }) {
            colors[it]?.let { computed ->
                if (!computed)
                    throw IllegalStateException("Found cyclic dependencies in task ${this.id}.")
                else false
            } ?: true
        }
    }

    private fun dfs(task: Task, prolog: (Task) -> Unit, epilog: (Task) -> Unit, condition: (Task) -> Boolean) {
        prolog(task)
        for (dep in task.dependencies) {
            if (condition(dep)) {
                dfs(dep, prolog, epilog, condition)
            }
        }
        epilog(task)
    }

    /**
     * Performs initial initialization during import of the whole Paddle project
     */
    open fun initialize() {
    }

    /**
     * Perform action which is the core essence of the task.
     *
     * Note, that in case Cacheable task [act] still will be called since it does not check for cache.
     */
    protected abstract fun act()

    /**
     * Perform action which is the core essence of the task. Unlike the [act] method, this one will be called as a coroutine.
     *
     * Note, that in case Cacheable task [act] still will be called since it does not check for cache.
     */
    protected open suspend fun actAsCoroutine() = act()

    /**
     * Run task with respect to the caches and current state.
     */
    open fun run() {
        runDependent()
        execute()
    }

    /**
     * Run task as a coroutine with concurrent execution of dependencies.
     */
    open suspend fun runAsCoroutine() {
        runDependentConcurrently()
        executeAsCoroutine()
    }

    protected fun execute() {
        project.terminal.commands.stdout(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.EXECUTE))

        try {
            act()
        } catch (e: ActException) {
            project.terminal.commands.stdout(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.FAILED))
            throw e
        }

        project.terminal.commands.stdout(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.DONE))
    }

    protected suspend fun executeAsCoroutine() = coroutineScope {
        if (isActive) {
            project.terminal.commands.stdout(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.EXECUTE))

            try {
                actAsCoroutine()
            } catch (e: ActException) {
                project.terminal.commands.stdout(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.FAILED))
                throw e
            }

            project.terminal.commands.stdout(CommandOutput.Command.Task(id, CommandOutput.Command.Task.Status.DONE))
        }
    }

    protected fun runDependent() {
        ensureAcyclicDeps
        for (dep in dependencies) {
            dep.run()
        }
    }

    protected suspend fun runDependentConcurrently() = coroutineScope {
        if (isActive) {
            ensureAcyclicDeps
            awaitAll(*dependencies.map { async { it.runAsCoroutine() } }.toTypedArray())
        }
    }

    class ActException(reason: String) : Exception(reason)
}
