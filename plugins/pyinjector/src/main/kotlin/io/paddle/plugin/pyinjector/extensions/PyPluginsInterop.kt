package io.paddle.plugin.pyinjector.extensions

import io.grpc.Channel
import io.paddle.plugin.pyinjector.interop.services.PaddleProjectsService
import io.paddle.plugin.pyinjector.interop.services.PyPluginsService
import io.paddle.project.Project
import io.paddle.utils.ext.Extendable

class PyPluginsInterop(val channel: Channel) {
    object Extension : Project.Extension<PyPluginsInterop> {
        override val key: Extendable.Key<PyPluginsInterop> = Extendable.Key()

        override fun create(project: Project): PyPluginsInterop {
            /*
            Services execution sequence:
                1. Paddle Projects API gRPC Server.
                2. Paddle PyPS.
                3. Client to Paddle PyPS for Paddle.
            */
            val port = PaddleProjectsService.getInstance(50051).also {
                it.register(project)
            }.port

            val channel = PyPluginsService.getInstance(paddleServicePort = port, serverPort = 50052).channelTo

            return PyPluginsInterop(channel)
        }
    }
}
