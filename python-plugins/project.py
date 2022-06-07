#!/usr/bin/env python3
import enum
from abc import ABC, abstractmethod
from dataclasses import dataclass, field
from typing import List

from config import PaddleProjectConfig
from config_spec import PaddleProjectConfigSpec


@enum.unique
class TaskDefaultGroups(enum.Enum):
    LINT = "lint"
    TEST = "test"
    BUILD = "build"
    APP = "application"


@enum.unique
class MessageType(enum.Enum):
    DEBUG = 0
    INFO = 1
    WARN = 2
    ERROR = 3
    OUT = 4
    ERR = 5


@dataclass
class Description:
    name: str
    version: str


@dataclass
class Roots:
    sources: List[str]
    tests: List[str]
    resources: List[str]


@dataclass
class Command:
    command: str
    args: List[str] = field(default_factory=list)


class PaddleProject(ABC):
    """
    Abstract class represents Paddle Project API with async methods.
    """

    @abstractmethod
    async def print_message(self, message: str, message_type: MessageType) -> None:
        pass

    @abstractmethod
    async def execute_command(self, command: Command) -> None:
        pass

    @property
    @abstractmethod
    def config(self) -> PaddleProjectConfig:
        pass

    @property
    @abstractmethod
    async def working_dir(self) -> str:
        pass

    @property
    @abstractmethod
    async def description(self) -> Description:
        pass

    @property
    @abstractmethod
    async def roots(self) -> Roots:
        pass

    @property
    @abstractmethod
    async def all_tasks_names(self) -> List[str]:
        pass

    @abstractmethod
    async def run_task(self, task_name: str) -> None:
        pass

    @abstractmethod
    async def add_source_paths(self, paths: List[str]) -> None:
        pass

    @abstractmethod
    async def add_tests_paths(self, paths: List[str]) -> None:
        pass

    @abstractmethod
    async def add_resources_paths(self, paths: List[str]) -> None:
        pass

    @abstractmethod
    async def add_clean_locations(self, paths: List[str]) -> None:
        pass


class ExtendedPaddleProject(PaddleProject):
    """
    Abstract class extends Paddle Project API with methods to extend project's configuration specification.
    """

    @property
    @abstractmethod
    def config_spec(self) -> PaddleProjectConfigSpec:
        pass
