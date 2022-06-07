#!/usr/bin/env python3

from config_spec import CompositeSpecNode, ArraySpecNode, StringSpecNode
import project_pb2 as project_api
from converters import to_composite_tree_spec, to_protobuf_composite


def create_protobuf_spec() -> project_api.CompositeSpecNode:
    result = project_api.CompositeSpecNode()
    result.title = "Paddle.build"
    result.description = "Configuration of Paddle build"

    result.required.append("descriptor")
    result.properties["descriptor"].composite.required.extend(["name", "version"])
    result.properties["descriptor"].composite.properties["name"].str.description = "Name of the project"
    result.properties["descriptor"].composite.properties["version"].str.description = "Version of the project"

    result.properties["roots"].composite.description = "Roots of the projects"
    result.properties["roots"].composite.properties["sources"].array.description = "Sources locations that should be used"
    result.properties["roots"].composite.properties["sources"].array.items.str.SetInParent()
    result.properties["roots"].composite.properties["tests"].array.description = "Tests locations that should be used"
    result.properties["roots"].composite.properties["tests"].array.items.str.SetInParent()
    result.properties["roots"].composite.properties["resources"].array.description = "Resources locations that should be used"
    result.properties["roots"].composite.properties["resources"].array.items.str.SetInParent()

    result.properties["executor"].composite.description = "Executor to be used by Paddle to execute build commands"
    result.properties["executor"].composite.properties["type"].str.valid.append("local")

    result.properties["plugins"].composite.properties["enabled"].array.description = "Array of enabled plugins identifiers"
    result.properties["plugins"].composite.properties["enabled"].array.items.str.SetInParent()
    result.properties["plugins"].composite.properties["jars"].array.description = "Array of paths to jars with custom plugins"
    result.properties["plugins"].composite.properties["jars"].array.items.str.SetInParent()

    result.properties["tasks"].composite.properties["linter"].composite.properties["mypy"].composite.properties["version"].str.SetInParent()
    result.properties["tasks"].composite.properties["linter"].composite.properties["pylint"].composite.properties["version"].str.SetInParent()

    result.properties["tasks"].composite.properties["tests"].composite.properties["pytest"].composite.properties["version"].str.SetInParent()
    result.properties["tasks"].composite.properties["run"].array.items.composite.properties["id"].str.SetInParent()
    result.properties["tasks"].composite.properties["run"].array.items.composite.properties["entrypoint"].str.SetInParent()

    return result


def create_tree_spec() -> CompositeSpecNode:
    result = CompositeSpecNode(title="Paddle.build", description="Configuration of Paddle build")

    result.required.append("descriptor")
    descriptor = CompositeSpecNode()
    descriptor.required.extend(["name", "version"])
    descriptor.properties["version"] = StringSpecNode(description="Version of the project")
    descriptor.properties["name"] = StringSpecNode(description="Name of the project")
    result.properties["descriptor"] = descriptor

    roots = CompositeSpecNode(description="Roots of the projects")
    roots.properties["sources"] = ArraySpecNode(description="Sources locations that should be used", items=StringSpecNode())
    roots.properties["tests"] = ArraySpecNode(description="Tests locations that should be used", items=StringSpecNode())
    roots.properties["resources"] = ArraySpecNode(description="Resources locations that should be used", items=StringSpecNode())
    result.properties["roots"] = roots

    executor = CompositeSpecNode(description="Executor to be used by Paddle to execute build commands")
    executor.properties["type"] = StringSpecNode(valid_values=["local"])
    result.properties["executor"] = executor

    plugins = CompositeSpecNode()
    plugins.properties["enabled"] = ArraySpecNode(description="Array of enabled plugins identifiers", items=StringSpecNode())
    plugins.properties["jars"] = ArraySpecNode(description="Array of paths to jars with custom plugins", items=StringSpecNode())
    result.properties["plugins"] = plugins

    tasks = CompositeSpecNode()
    linter = CompositeSpecNode()
    mypy = CompositeSpecNode()
    mypy.properties["version"] = StringSpecNode()
    linter.properties["mypy"] = mypy
    pylint = CompositeSpecNode()
    pylint.properties["version"] = StringSpecNode()
    linter.properties["pylint"] = pylint
    tasks.properties["linter"] = linter

    tests = CompositeSpecNode()
    pytest = CompositeSpecNode()
    pytest.properties["version"] = StringSpecNode()
    tests.properties["pytest"] = pytest
    tasks.properties["tests"] = tests
    items = CompositeSpecNode()
    items.properties["id"] = StringSpecNode()
    items.properties["entrypoint"] = StringSpecNode()
    tasks.properties["run"] = ArraySpecNode(items=items)
    result.properties["tasks"] = tasks

    return result


def prepare_data() -> (project_api.CompositeSpecNode, CompositeSpecNode):
    return create_protobuf_spec(), create_tree_spec()


protobuf_spec, tree_spec = prepare_data()


def test_from_protobuf_convert():
    assert to_composite_tree_spec(protobuf_spec) == tree_spec


def test_to_protobuf_convert():
    assert to_protobuf_composite(tree_spec) == protobuf_spec


def test_all():
    test_from_protobuf_convert()
    test_to_protobuf_convert()


if __name__ == '__main__':
    test_all()
