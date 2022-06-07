package io.paddle.plugin.pyinjector.dependencies

import io.paddle.plugin.pyinjector.dependencies.repositories.PyModuleRepository
import java.nio.file.Path

data class PyModule(val relativePathTo: Path, val repository: PyModuleRepository) {
    val absolutePathTo: Path
        get() = repository.absolutePathTo.resolve(relativePathTo)
}
