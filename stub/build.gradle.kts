import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm")
    id("com.google.protobuf")
}

sourceSets {
    main {
        proto {
            srcDirs("src/proto")
        }
        java {
            srcDirs("generated/main/grpc_java")
            srcDirs("generated/main/java")
            srcDirs("generated/main/kotlin")
            srcDirs("generated/main/grpckt")
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
}

protobuf {
    generatedFilesBaseDir = "$projectDir/generated"

    protoc {
        artifact = "com.google.protobuf:protoc:3.19.2"
    }
    plugins {
        id("grpc_java") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.39.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.2.1:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc_java")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    api("io.grpc:grpc-protobuf:1.44.0")
    api("com.google.protobuf:protobuf-java-util:3.19.4")
    api("com.google.protobuf:protobuf-kotlin:3.19.4")
    api("io.grpc:grpc-kotlin-stub:1.2.1")

    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}
