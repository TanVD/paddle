#!/usr/bin/env python3
from typing import Dict

from config import PaddleProjectConfig


class PaddleProjectConfigImpl(PaddleProjectConfig):
    def __init__(self, config_dict: Dict[str, object]):
        self.__config = config_dict

    def contains(self, key: str) -> bool:
        return self.__config is not None and self.get(key) is not None

    def get(self, key: str):
        parts = key.strip().split(".")
        if not parts:
            return None
        path = parts[:-1]
        name = parts[-1]
        current = self.__config
        for part in path:
            current = current[part]
            if not isinstance(current, dict):
                return None
        return current[name]
