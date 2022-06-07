#!/usr/bin/env python3
from pathlib import Path
from typing import List

import yaml
from config_impl import PaddleProjectConfigImpl
from config_spec_impl import PaddleProjectConfigSpecImpl
from paddle_client import AsyncPaddleApiClient
from plugin import ExtendedPaddleProject
from project import MessageType, Description, Roots, Command


class ExtendedPaddleProjectImpl(ExtendedPaddleProject):
    def __init__(self, project_id: str, working_dir: str, grpc_client: AsyncPaddleApiClient) -> None:
        self.__project_id = project_id
        self.__working_dir = working_dir
        self.__paddle_client = grpc_client
        self.__paddle_project_config = None
        self.__paddle_project_config_path = Path(self.__working_dir).resolve().joinpath("paddle.yaml")
        self.reload_config()
        self.__paddle_project_config_spec = PaddleProjectConfigSpecImpl(self.__project_id, self.__paddle_client)

    async def print_message(self, message: str, message_type: MessageType) -> None:
        await self.__paddle_client.print_message(self.__project_id, message, message_type.name)

    async def execute_command(self, command: Command) -> None:
        await self.__paddle_client.execute_command(self.__project_id, command.command, command.args)

    @property
    async def working_dir(self) -> str:
        return await self.__paddle_client.get_working_dir(self.__project_id)

    @property
    async def description(self) -> Description:
        return await self.__paddle_client.get_description(self.__project_id)

    @property
    async def roots(self) -> Roots:
        return await self.__paddle_client.get_roots(self.__project_id)

    @property
    async def all_tasks_names(self) -> List[str]:
        return await self.__paddle_client.get_tasks_names(self.__project_id)

    async def run_task(self, task_name: str) -> None:
        await self.__paddle_client.run_task(self.__project_id, task_name)

    async def add_source_paths(self, paths: List[str]) -> None:
        await self.__paddle_client.add_sources(self.__project_id, paths)

    async def add_tests_paths(self, paths: List[str]) -> None:
        await self.__paddle_client.add_tests(self.__project_id, paths)

    async def add_resources_paths(self, paths: List[str]) -> None:
        await self.__paddle_client.add_resources(self.__project_id, paths)

    async def add_clean_locations(self, paths: List[str]) -> None:
        await self.__paddle_client.add_clean_location(self.__project_id, paths)

    @property
    def config_spec(self) -> PaddleProjectConfigSpecImpl:
        return self.__paddle_project_config_spec

    def reset_config_spec(self) -> None:
        self.__paddle_project_config_spec.reset()

    @property
    def config(self) -> PaddleProjectConfigImpl:
        return self.__paddle_project_config

    def reload_config(self) -> None:
        with open(str(self.__paddle_project_config_path), "r") as stream:
            try:
                self.__paddle_project_config = PaddleProjectConfigImpl(yaml.safe_load(stream))
            except yaml.YAMLError as exc:
                print(exc)
