from plugin import plugin, PaddlePlugin, PaddleTask
from project import TaskDefaultGroups, MessageType, Command
from config_spec import CompositeSpecNode, StringSpecNode


@plugin(name="greeting")
class GreetingPlugin(PaddlePlugin):
    async def configure(self, project):
        pass

    async def tasks(self, project):
        return [GreetingTask(project)]


class GreetingTask(PaddleTask):
    def __init__(self, project) -> None:
        PaddleTask.__init__(self, project, "greet", TaskDefaultGroups.APP, deps=[])

    async def initialize(self) -> None:
        pass

    async def act(self) -> None:
        await self.project.print_message("Hello, world!", MessageType.OUT)


@plugin(name="descriptor-writer")
class DescriptorWriter(PaddlePlugin):
    async def configure(self, project):
        project.config_spec.root.properties["desc-writer"] = CompositeSpecNode(required=["dir-name", "file-name"])
        project.config_spec.root.properties["desc-writer"].properties["dir-name"] = StringSpecNode()
        project.config_spec.root.properties["desc-writer"].properties["file-name"] = StringSpecNode()

    async def tasks(self, project):
        return [MkdirTask(project), CreateFileTask(project), WriteDescrioptorTask(project)]


class MkdirTask(PaddleTask):
    def __init__(self, project) -> None:
        PaddleTask.__init__(self, project, "mkdir", TaskDefaultGroups.TEST.value, deps=[])

    async def initialize(self) -> None:
        await self.project.print_message("Initialize mkdir task...", MessageType.INFO)
        dir_name = self.project.config.string("desc-writer.dir-name")
        work_dir = await self.project.working_dir
        await self.project.add_clean_locations([f"{work_dir}/{dir_name}"])

    async def act(self) -> None:
        dir_name = self.project.config.string("desc-writer.dir-name")
        await self.project.print_message(f"Creating directory {dir_name}...", MessageType.INFO)
        await self.project.execute_command(Command(command="mkdir", args=[dir_name]))


class CreateFileTask(PaddleTask):
    def __init__(self, project) -> None:
        PaddleTask.__init__(self, project, "createfile", TaskDefaultGroups.TEST, deps=["mkdir"])

    async def initialize(self) -> None:
        await self.project.print_message("Initialize create file task...", MessageType.INFO)

    async def act(self) -> None:
        dir_name = self.project.config.string("desc-writer.dir-name")
        file_name = self.project.config.string("desc-writer.file-name")
        await self.project.print_message(f"Creating file {file_name}...", MessageType.INFO)
        await self.project.execute_command(Command(command="touch", args=[f"./{dir_name}/{file_name}"]))


class WriteDescrioptorTask(PaddleTask):
    def __init__(self, project) -> None:
        PaddleTask.__init__(self, project, "write-descriptor", TaskDefaultGroups.TEST, deps=["createfile"])

    async def initialize(self) -> None:
        await self.project.print_message("Initialize write descriptor task...", MessageType.INFO)

    async def act(self) -> None:
        description = await self.project.description
        name = description.name
        version = description.version
        dir_name = self.project.config.string("desc-writer.dir-name")
        file_name = self.project.config.string("desc-writer.file-name")
        with open(f"./{dir_name}/{file_name}", "w") as file:
            file.write(f"name: {name} -- version: {version}")
