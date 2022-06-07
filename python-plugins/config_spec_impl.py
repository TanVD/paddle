#!/usr/bin/env python3
from config_spec import PaddleProjectConfigSpec, SpecNode, CompositeSpecNode
from paddle_client import AsyncPaddleApiClient


class PaddleProjectConfigSpecImpl(PaddleProjectConfigSpec):
    def __init__(self, project_id: str, grpc_client: AsyncPaddleApiClient) -> None:
        self.__project_id = project_id
        self.__grpc_client = grpc_client
        self.__root = None

    @property
    def root(self) -> CompositeSpecNode:
        assert self.__root is not None
        return self.__root

    def contains(self, key: str) -> bool:
        return self.__root is not None and self.__root.get(key) is not None

    def get(self, key: str) -> SpecNode:
        assert self.__root is not None

        parts = key.strip().split(".")
        if not parts:
            return self.__root
        path = parts[:-1]
        name = parts[-1]
        current = self.__root
        for part in path:
            current = current.properties[part]
            if not isinstance(current, CompositeSpecNode):
                return None
        return current.properties[name]

    def get_nearest(self, key: str) -> (SpecNode, str):
        assert self.__root is not None

        parts = key.strip().split(".")
        if not parts:
            return self.__root, key
        path = parts[:-1]
        name = parts[-1]
        current = self.__root
        for i, part in enumerate(path, start=1):
            current = current.properties[part]
            if not isinstance(current, CompositeSpecNode):
                return current, ".".join(path[i:] + [name])
        return current.properties[name], ""

    async def load_config_spec(self) -> None:
        if not self.__root:
            self.__root = await self.__grpc_client.get_configuration_specification(self.__project_id)

    async def store_config_spec(self) -> None:
        assert self.__root is not None
        await self.__grpc_client.update_configuration_specification(self.__project_id, self.__root)

    def reset(self) -> None:
        self.__root = None
