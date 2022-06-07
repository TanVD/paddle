#!/usr/bin/env python3
from abc import ABC, abstractmethod
from typing import List, Dict


class PaddleProjectConfig(ABC):
    """
    Abstract class represents Paddle Project configuration.
    """

    @abstractmethod
    def contains(self, key: str) -> bool:
        pass

    @abstractmethod
    def get(self, key: str) -> object:
        """
        Returns configuration value by key, e.g. "root.src" returns list of sources.
        :param key: string represents path in configuration tree
        """
        pass

    def dict(self, key: str) -> Dict[str, object]:
        result = self.get(key)
        if isinstance(result, Dict):
            return result
        else:
            return None

    def list(self, key: str) -> List[object]:
        result = self.get(key)
        if isinstance(result, List):
            return result
        else:
            return None

    def string(self, key: str) -> str:
        result = self.get(key)
        if isinstance(result, str):
            return result
        else:
            return None

    def integer(self, key: str) -> int:
        result = self.get(key)
        if isinstance(result, int):
            return result
        else:
            return None

    def boolean(self, key: str) -> bool:
        result = self.get(key)
        if isinstance(result, bool):
            return result
        else:
            return None
