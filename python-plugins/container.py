#!/usr/bin/env python3
import importlib
import os
import sys
from dataclasses import dataclass
from pathlib import Path
from typing import List, Iterable, Dict

from grpc.aio import Channel

from paddle_client import AsyncPaddleApiClient
from plugin import PaddlePlugin, PaddleTask
from project_impl import ExtendedPaddleProjectImpl


@dataclass
class PyPackageInfo:
    package_name: str
    distribution_url: str


@dataclass
class PyModuleInfo:
    with_repo_dir: str
    relative_path_to_module: str


class PaddlePluginsProvider:
    def __init__(self, plugins_site_packages_path: Path) -> None:
        self.__plugins = None
        self.__packages_content = None
        if str(plugins_site_packages_path) not in sys.path:
            sys.path.append(str(plugins_site_packages_path))

    @staticmethod
    def eval_package_hash(package_info: PyPackageInfo):
        return package_info.distribution_url

    @staticmethod
    def eval_module_hash(module_info: PyModuleInfo):
        return f"{module_info.with_repo_dir}:{module_info.relative_path_to_module}"

    @staticmethod
    def eval_plugin_hash(plugin_name: str, source: str):
        return f"{plugin_name}:{source}"

    @staticmethod
    def import_plugins_from(module_name: str, package_name: str) -> Dict[str, PaddlePlugin]:
        module = importlib.import_module(name=f".{module_name}", package=package_name)
        return getattr(module, "__paddle_plugins__")

    def import_plugins_from_site_packages(self, packages_info: Iterable[PyPackageInfo]) -> None:
        updated_plugins = dict()
        updated_packages = dict()
        if not self.__plugins:
            self.__plugins = updated_plugins
        if not self.__packages_content:
            self.__packages_content = updated_packages

        for package_info in packages_info:
            package_hash = self.eval_package_hash(package_info)
            if package_hash in self.__packages_content:
                plugins_hashes = self.__packages_content[package_hash]
                updated_packages[package_hash] = plugins_hashes
                for plugin_hash in plugins_hashes:
                    updated_plugins[plugin_hash] = self.__plugins[plugin_hash]
            else:
                plugins_data = self.import_plugins_from("plugins", package_info.package_name)
                updated_packages[package_hash] = list()
                for (name, plugin) in plugins_data.items():
                    plugin_hash = self.eval_plugin_hash(name, package_hash)
                    updated_plugins[plugin_hash] = plugin
                    updated_packages[package_hash].append(plugin_hash)

        self.__plugins = updated_plugins
        self.__packages_content = updated_packages

    def import_plugins_from_modules(self, modules_info: Iterable[PyModuleInfo]) -> None:
        for module_info in modules_info:
            self.import_plugins_from_module(module_info)

    @staticmethod
    def parse_module_path(path: str) -> (str, str):
        real_path = Path(path)
        return str(real_path.parent).lstrip(os.path.sep).rstrip(os.path.sep).replace(os.path.sep, "."), real_path.stem

    def import_plugins_from_module(self, module_info: PyModuleInfo):
        if module_info.with_repo_dir not in sys.path:
            sys.path.append(module_info.with_repo_dir)
        module_package, module_name = self.parse_module_path(module_info.relative_path_to_module)
        plugins_data = self.import_plugins_from(module_name, module_package)
        module_hash = self.eval_module_hash(module_info)
        for (name, plugin) in plugins_data.items():
            plugin_hash = self.eval_plugin_hash(name, module_hash)
            self.__plugins[plugin_hash] = plugin

    def __getitem__(self, plugin_hash: str) -> PaddlePlugin:
        return self.__plugins[plugin_hash]


class PaddleProjectContainer:
    def __init__(self, project_id: str, working_dir: str, plugins_dir: str, channel_to_paddle: Channel) -> None:
        self.__project = ExtendedPaddleProjectImpl(project_id, working_dir, AsyncPaddleApiClient(grpc_channel=channel_to_paddle))
        self.__plugins_provider = PaddlePluginsProvider(Path(working_dir).resolve().joinpath(plugins_dir))
        self.__tasks = dict()

    def import_package_plugins(self, plugins: Iterable[PyPackageInfo]) -> None:
        self.__plugins_provider.import_plugins_from_site_packages(plugins)

    def import_module_plugins(self, plugins: Iterable[PyModuleInfo]) -> None:
        self.__plugins_provider.import_plugins_from_modules(plugins)

    async def configure_plugin(self, plugin_hash: str) -> None:
        await self.__project.config_spec.load_config_spec()
        await self.__plugins_provider[plugin_hash].configure(self.__project)
        await self.__project.config_spec.store_config_spec()

    async def plugin_tasks(self, plugin_hash: str) -> List[PaddleTask]:
        tasks = await self.__plugins_provider[plugin_hash].tasks(self.__project)
        for task in tasks:
            self.__tasks[task.identifier] = task
        return tasks

    def task(self, task_id: str) -> PaddleTask:
        return self.__tasks[task_id]

    def reload(self) -> None:
        self.__project.reload_config()
        self.__project.reset_config_spec()
        self.__tasks = dict()
