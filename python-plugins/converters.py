#!/usr/bin/env python3

from config_spec import CompositeSpecNode, ArraySpecNode, StringSpecNode, BooleanSpecNode, IntegerSpecNode, SpecNode
import project_pb2 as project_api


def to_composite_tree_spec(protobuf: project_api.CompositeSpecNode) -> CompositeSpecNode:
    title = protobuf.title if protobuf.title else None
    description = protobuf.description if protobuf.description else None
    root = CompositeSpecNode(title=title, description=description)
    root.required.extend(protobuf.required)
    root.valid_specs.extend(map(to_composite_tree_spec, protobuf.valid))
    for (name, node) in protobuf.properties.items():
        root.properties[name] = to_tree_spec(node)
    return root


def to_array_tree_spec(protobuf: project_api.ArraySpecNode) -> ArraySpecNode:
    title = protobuf.title if protobuf.title else None
    description = protobuf.description if protobuf.description else None
    return ArraySpecNode(title=title, description=description, items=to_tree_spec(protobuf.items))


def to_string_tree_spec(protobuf: project_api.StringSpecNode) -> StringSpecNode:
    title = protobuf.title if protobuf.title else None
    description = protobuf.description if protobuf.description else None
    return StringSpecNode(title=title, description=description, valid_values=protobuf.valid)


def to_integer_tree_spec(protobuf: project_api.IntegerSpecNode) -> IntegerSpecNode:
    title = protobuf.title if protobuf.title else None
    description = protobuf.description if protobuf.description else None
    return IntegerSpecNode(title=title, description=description, valid_values=protobuf.valid)


def to_boolean_tree_spec(protobuf: project_api.BooleanSpecNode) -> BooleanSpecNode:
    title = protobuf.title if protobuf.title else None
    description = protobuf.description if protobuf.description else None
    return BooleanSpecNode(title=title, description=description, valid_values=protobuf.valid)


to_spec_mapper = {"composite": lambda node: to_composite_tree_spec(node.composite), "array": lambda node: to_array_tree_spec(node.array),
                  "str": lambda node: to_string_tree_spec(node.str), "boolean": lambda node: to_boolean_tree_spec(node.boolean),
                  "integer": lambda node: to_integer_tree_spec(node.integer)}


def to_tree_spec(protobuf: project_api.SpecNode) -> SpecNode:
    return to_spec_mapper[protobuf.WhichOneof("actual")](protobuf)


def to_protobuf_composite(composite: CompositeSpecNode) -> project_api.CompositeSpecNode:
    result = project_api.CompositeSpecNode()
    if composite.title is not None:
        result.title = composite.title
    if composite.description is not None:
        result.description = composite.description
    result.required.extend(composite.required)
    result.valid.extend(map(to_protobuf_composite, composite.valid_specs))
    for (name, node) in composite.properties.items():
        result.properties[name].CopyFrom(to_protobuf_spec(node))
    return result


def to_protobuf_arr(array: ArraySpecNode) -> project_api.ArraySpecNode:
    node = project_api.ArraySpecNode()
    if array.title is not None:
        node.title = array.title
    if array.description is not None:
        node.description = array.description
    node.items.CopyFrom(to_protobuf_spec(array.items))
    return node


def to_protobuf_str(string: StringSpecNode) -> project_api.StringSpecNode:
    node = project_api.StringSpecNode()
    if string.title is not None:
        node.title = string.title
    if string.description is not None:
        node.description = string.description
    node.valid.extend(string.valid_values)
    return node


def to_protobuf_int(integer: IntegerSpecNode) -> project_api.IntegerSpecNode:
    node = project_api.IntegerSpecNode()
    if integer.title is not None:
        node.title = integer.title
    if integer.description is not None:
        node.description = integer.description
    node.valid.extend(integer.valid_values)
    return node


def to_protobuf_bool(boolean: BooleanSpecNode) -> project_api.BooleanSpecNode:
    node = project_api.BooleanSpecNode()
    if boolean.title is not None:
        node.title = boolean.title
    if boolean.description is not None:
        node.description = boolean.description
    node.valid.extend(boolean.valid_values)
    return node


def to_protobuf_spec(node: SpecNode) -> project_api.SpecNode:
    if isinstance(node, CompositeSpecNode):
        result = project_api.SpecNode()
        result.composite.CopyFrom(to_protobuf_composite(node))
    elif isinstance(node, ArraySpecNode):
        result = project_api.SpecNode()
        result.array.CopyFrom(to_protobuf_arr(node))
    elif isinstance(node, StringSpecNode):
        result = project_api.SpecNode()
        result.str.CopyFrom(to_protobuf_str(node))
    elif isinstance(node, IntegerSpecNode):
        result = project_api.SpecNode()
        result.integer.CopyFrom(to_protobuf_int(node))
    elif isinstance(node, BooleanSpecNode):
        result = project_api.SpecNode()
        result.boolean.CopyFrom(to_protobuf_bool(node))
    else:
        raise KeyError("Unknown type of specification tree node")
    return result
