#!/usr/bin/env python3
from typing import List

from converters import to_composite_tree_spec, to_protobuf_spec
from config_spec import CompositeSpecNode
import project_pb2 as project_api
import plugins_pb2 as plugins_api
import project_pb2_grpc as project_servicer
from project import Roots, Description


class AsyncPaddleApiClient:
    def __init__(self, grpc_channel) -> None:
        self.stub = project_servicer.ProjectStub(grpc_channel)

    async def print_message(self, project_id: str, message: str, type_name: str) -> None:
        await self.stub.PrintMessage(project_api.PrintRequest(
            projectId=project_id, message=message, type=project_api.PrintRequest.Type.Value(type_name))
        )

    async def execute_command(self, project_id: str, command: str, args: List[str]) -> None:
        request = project_api.ExecuteCommandRequest(projectId=project_id, command=command)
        request.args.extend(args)
        await self.stub.ExecuteCommand(request)

    async def get_working_dir(self, project_id: str) -> str:
        response = await self.stub.GetWorkingDirectory(project_api.ProjectInfoRequest(projectId=project_id))
        return response.path

    async def get_description(self, project_id: str) -> Description:
        response = await self.stub.GetDescription(project_api.ProjectInfoRequest(projectId=project_id))
        return Description(name=response.name, version=response.version)

    async def get_roots(self, project_id: str) -> Roots:
        response = await self.stub.GetRoots(project_api.ProjectInfoRequest(projectId=project_id))
        return Roots(sources=response.sources, tests=response.tests, resources=response.resources)

    async def add_sources(self, project_id: str, paths: List[str]) -> None:
        request = project_api.AddPathsRequest(projectId=project_id)
        request.paths.extend(paths)
        await self.stub.AddSources(request)

    async def add_tests(self, project_id: str, paths: List[str]) -> None:
        request = project_api.AddPathsRequest(projectId=project_id)
        request.paths.extend(paths)
        await self.stub.AddTests(request)

    async def add_resources(self, project_id: str, paths: List[str]) -> None:
        request = project_api.AddPathsRequest(projectId=project_id)
        request.paths.extend(paths)
        await self.stub.AddResources(request)

    async def get_tasks_names(self, project_id: str) -> List[str]:
        response = await self.stub.GetTasksNames(project_api.ProjectInfoRequest(projectId=project_id))
        return response.names

    async def run_task(self, project_id: str, task_id: str) -> None:
        await self.stub.RunTask(plugins_api.ProcessTaskRequest(projectId=project_id, taskId=task_id))

    async def add_clean_location(self, project_id: str, paths: List[str]) -> None:
        request = project_api.AddPathsRequest(projectId=project_id)
        request.paths.extend(paths)
        await self.stub.AddCleanLocation(request)

    async def get_configuration_specification(self, project_id: str) -> CompositeSpecNode:
        return to_composite_tree_spec(await self.stub.GetConfigurationSpecification(
            project_api.ProjectInfoRequest(projectId=project_id)
        ))

    async def update_configuration_specification(self, project_id: str, config_spec: CompositeSpecNode) -> None:
        await self.stub.UpdateConfigurationSpecification(
            project_api.UpdateConfigSpecRequest(projectId=project_id, configSpec=to_protobuf_spec(config_spec))
        )
