group = rootProject.group
version = rootProject.version

plugins {
    kotlin("plugin.serialization") version "1.5.10" apply true
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "src/gen/java", "src/gen/kotlin")
    }
}

dependencies {
    api(project(":stub"))

    api("org.apache.commons", "commons-collections4", "4.4")

    implementation("org.codehaus.plexus", "plexus-utils", "3.3.0")
    implementation("commons-codec", "commons-codec", "1.15")

    implementation("com.github.ajalt.clikt", "clikt", "3.2.0")

    implementation("org.snakeyaml", "snakeyaml-engine", "2.3")

    implementation("com.charleskorn.kaml", "kaml", "0.34.0")
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.2.1")

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))

    implementation("com.google.protobuf:protobuf-java:3.19.0")
    implementation("io.grpc:grpc-netty-shaded:1.44.0")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}

tasks.test {
    useJUnitPlatform()
}
