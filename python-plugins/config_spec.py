#!/usr/bin/env python3
from dataclasses import dataclass, field
from abc import ABC, abstractmethod
from typing import List, Dict


@dataclass
class SpecNode(ABC):
    """
    Base class of all configuration specification nodes types.
    """
    title: str = None
    description: str = None


@dataclass
class CompositeSpecNode(SpecNode):
    required: List[str] = field(default_factory=list)
    properties: Dict[str, SpecNode] = field(default_factory=dict)
    valid_specs: List['CompositeSpecNode'] = field(default_factory=list)


@dataclass
class ArraySpecNode(SpecNode):
    items: SpecNode = None


@dataclass
class StringSpecNode(SpecNode):
    valid_values: List[str] = field(default_factory=list)


@dataclass
class BooleanSpecNode(SpecNode):
    valid_values: List[bool] = field(default_factory=list)


@dataclass
class IntegerSpecNode(SpecNode):
    valid_values: List[int] = field(default_factory=list)


class PaddleProjectConfigSpec(ABC):
    """
    Abstract class represents Paddle Project configuration specification.
    Implementation of this class should provide API to change specification
    which used to validate Paddle project's configuration.
    """

    @property
    @abstractmethod
    def root(self) -> CompositeSpecNode:
        pass

    @abstractmethod
    def contains(self, key: str) -> bool:
        """
        Checks that configuration specification contains the value specified by key.
        :param key: boolean flag represents the answer
        """
        pass

    @abstractmethod
    def get_nearest(self, key: str) -> (SpecNode, str):
        """
        Returns configuration specification value by key or nearest top-level presented if original does not exist.
        Value type is one of the SpecNode implementation.
        :param key: string represents path in configuration specification tree
        :returns pair consists of the nearest top-level presented value and rest path corresponding to non-existence part
        """
        pass

    @abstractmethod
    def get(self, key: str) -> SpecNode:
        """
        Returns configuration specification value by key or None if value not present.
        Value type is one of the SpecNode implementation.
        :param key: string represents path in configuration specification tree
        """
        pass

    def list(self, key: str) -> ArraySpecNode:
        """
        Returns ArraySpecNode from configuration specification by key or None if value not present.
        :param key: string represents path in configuration specification tree
        """
        result = self.get(key)
        if isinstance(result, ArraySpecNode):
            return result
        else:
            return None

    def string(self, key: str) -> StringSpecNode:
        """
        Returns StringSpecNode from configuration specification by key or None if value not present.
        :param key: string represents path in configuration specification tree
        """
        result = self.get(key)
        if isinstance(result, StringSpecNode):
            return result
        else:
            return None

    def boolean(self, key: str) -> BooleanSpecNode:
        """
        Returns BooleanSpecNode from configuration specification by key or None if value not present.
        :param key: string represents path in configuration specification tree
        """
        result = self.get(key)
        if isinstance(result, BooleanSpecNode):
            return result
        else:
            return None

    def integer(self, key: str) -> IntegerSpecNode:
        """
        Returns IntegerSpecNode from configuration specification by key or None if value not present.
        :param key: string represents path in configuration specification tree
        """
        result = self.get(key)
        if isinstance(result, IntegerSpecNode):
            return result
        else:
            return None
