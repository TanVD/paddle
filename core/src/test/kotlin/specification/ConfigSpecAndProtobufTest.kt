package specification

import io.paddle.plugin.interop.*
import io.paddle.specification.tree.CompositeSpecTreeNode
import io.paddle.utils.config.specification.NodesMapper
import org.junit.jupiter.api.Test
import specification.TestCommon.parseAndTypingTreeFrom
import kotlin.test.assertEquals

internal class ConfigSpecAndProtobufTest {
    @Test
    fun `protobuf and spec tree test`() {
        val configSpecTree: CompositeSpecTreeNode = parseAndTypingTreeFrom(TestCommon.schemas[0])

        val protobufSpecTree: CompositeSpecNode =
            compositeSpecNode {
                title = "Paddle.build"
                description = "Configuration of Paddle build"
                required.add("descriptor")
                properties.putAll(mapOf(
                    "descriptor" to specNode {
                        composite = compositeSpecNode {
                            required.addAll(listOf("name", "version"))
                            properties.putAll(mapOf(
                                "name" to specNode {
                                    str = stringSpecNode {
                                        description = "Name of the project"
                                    }
                                },
                                "version" to specNode {
                                    str = stringSpecNode {
                                        description = "Version of the project"
                                    }
                                }
                            ))
                        }
                    },
                    "roots" to specNode {
                        composite = compositeSpecNode {
                            description = "Roots of the projects"
                            properties.putAll(mapOf(
                                "sources" to specNode {
                                    array = arraySpecNode {
                                        description = "Sources locations that should be used"
                                        items = specNode {
                                            str = stringSpecNode {  }
                                        }
                                    }
                                },
                                "tests" to specNode {
                                    array = arraySpecNode {
                                        description = "Tests locations that should be used"
                                        items = specNode {
                                            str = stringSpecNode {  }
                                        }
                                    }
                                },
                                "resources" to specNode {
                                    array = arraySpecNode {
                                        description = "Resources locations that should be used"
                                        items = specNode {
                                            str = stringSpecNode {  }
                                        }
                                    }
                                }

                            ))
                        }
                    },
                    "executor" to specNode {
                        composite = compositeSpecNode {
                            description = "Executor to be used by Paddle to execute build commands"
                            properties.putAll(mapOf(
                                "type" to specNode {
                                    str = stringSpecNode {
                                        valid.add("local")
                                    }
                                }
                            ))
                        }
                    },
                    "plugins" to specNode {
                        composite = compositeSpecNode {
                            properties.putAll(mapOf(
                                "enabled" to specNode {
                                    array = arraySpecNode {
                                        description = "Array of enabled plugins identifiers"
                                        items = specNode {
                                            str = stringSpecNode {  }
                                        }
                                    }
                                },
                                "jars" to specNode {
                                    array = arraySpecNode {
                                        description = "Array of paths to jars with custom plugins"
                                        items = specNode {
                                            str = stringSpecNode {  }
                                        }
                                    }
                                }
                            ))
                        }
                    },
                    "tasks" to specNode {
                        composite = compositeSpecNode {
                            properties.putAll(mapOf(
                                "linter" to specNode {
                                    composite = compositeSpecNode {
                                        properties.putAll(mapOf(
                                            "mypy" to specNode {
                                                composite = compositeSpecNode {
                                                    properties.put("version", specNode { str = stringSpecNode {  } })
                                                }
                                            },
                                            "pylint" to specNode {
                                                composite = compositeSpecNode {
                                                    properties.put("version", specNode { str = stringSpecNode {  } })
                                                }
                                            }
                                        ))
                                    }
                                },
                                "tests" to specNode {
                                    composite = compositeSpecNode {
                                        properties.put("pytest", specNode {
                                            composite = compositeSpecNode {
                                                properties.put("version", specNode { str = stringSpecNode {  } })
                                            }
                                        })
                                    }
                                },
                                "run" to specNode {
                                    array = arraySpecNode {
                                        items = specNode {
                                            composite = compositeSpecNode {
                                                properties.putAll(mapOf(
                                                    "id" to specNode {
                                                        str = stringSpecNode {  }
                                                    },
                                                    "entrypoint" to specNode {
                                                        str = stringSpecNode {  }
                                                    }
                                                ))
                                            }
                                        }
                                    }
                                }
                            ))
                        }
                    }
                ))
            }

        assertEquals(protobufSpecTree, NodesMapper.toProtobufMessage(configSpecTree))
        assertEquals(configSpecTree, NodesMapper.toConfigSpec(specNode { composite = protobufSpecTree }))
    }
}
