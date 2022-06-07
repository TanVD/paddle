#!/usr/bin/env python3
import sys
from abc import ABC, abstractmethod
from typing import List

from project import PaddleProject, ExtendedPaddleProject, TaskDefaultGroups


class PaddleTask(ABC):
    """
    Abstract class represents Paddle Task.
    """

    @abstractmethod
    def __init__(self, project: PaddleProject, identifier: str, group: TaskDefaultGroups, deps: List[str]) -> None:
        self.__project = project
        self.__id = identifier
        self.__group = group.value
        self.__deps = deps

    @property
    def identifier(self) -> str:
        """
        Identifier of the task in the project.
        Note that this identifier will be used to call the task from the terminal.

        :return: string represents the task identifier
        """
        return self.__id

    @property
    def group(self) -> str:
        """
        Tasks are grouped into categories by semantics. Use TaskDefaultGroups for standard groups for tasks.

        :return: string represents the task group
        """
        return self.__group

    @property
    def deps(self) -> List[str]:
        """
        Names of dependencies tasks that should be called before this task.

        :return: list of strings represent names of dependencies
        """
        return self.__deps

    @property
    def project(self):
        """
        Names of dependencies tasks that should be called before this task.

        :return: list of strings represent names of dependencies
        """
        return self.__project

    async def initialize(self) -> None:
        """
        Performs initial initialization during import of the whole Paddle project.
        Note that such methods for all tasks execute only in a sequential manner within Paddle Project.
        """
        pass

    @abstractmethod
    async def act(self) -> None:
        """
        Perform action which is the core essence of the task.
        Note that this method can be called as a coroutine within Paddle Project.
        """
        pass


class PaddlePlugin(ABC):
    """
    Abstract class represents Paddle Plugin.
    Note that methods are async in order to use async Paddle Project API calls.
    """

    @abstractmethod
    async def tasks(self, project: PaddleProject) -> List[PaddleTask]:
        """
        Returns list of plugin's tasks.
        Note that project API instance has no methods to change configuration of Paddle project. Use configure instead.
        :param project: project API
        :return: list of tasks
        """
        pass

    @abstractmethod
    async def configure(self, project: ExtendedPaddleProject) -> None:
        """
        Configures a project with the plugin via Paddle Project API.
        Note that project API instance extended by the methods to change configuration of Paddle project.
        :param project: project API
        """
        pass


def plugin(name: str):
    """
    Decorates Paddle Plugin implementation class.
    :param name: name of the plugin
    """

    def inner(plugin_class):
        plugins_attr_name = "__paddle_plugins__"
        module = sys.modules[plugin_class.__module__]
        if hasattr(module, plugins_attr_name):
            plugins = getattr(module, plugins_attr_name)
            plugins[name] = plugin_class()
        else:
            __paddle_plugins__ = {name: plugin_class()}
            setattr(module, plugins_attr_name, __paddle_plugins__)
        return plugin_class

    return inner
