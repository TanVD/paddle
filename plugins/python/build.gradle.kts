group = rootProject.group
version = rootProject.version

plugins {
    kotlin("plugin.serialization") version "1.6.21" apply true
    java
}

dependencies {
    implementation(project(":core"))

    implementation("org.antlr:antlr4-runtime:4.8")
    implementation("javax.mail:mail:1.4.7")

    implementation("org.jsoup:jsoup:1.14.2")
    implementation("io.ktor:ktor-client-core:1.6.3")
    implementation("io.ktor:ktor-client-cio:1.6.3")
    implementation("io.ktor:ktor-client-auth:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")

    implementation("org.yaml:snakeyaml:1.30")
    implementation("org.snakeyaml:snakeyaml-engine:2.3")
    implementation("org.ini4j:ini4j:0.5.4")

    implementation("org.codehaus.plexus:plexus-archiver:4.4.0")
    implementation("org.codehaus.plexus:plexus-utils:3.4.1")

    // https://mvnrepository.com/artifact/com.github.javakeyring/java-keyring
    implementation("com.github.javakeyring:java-keyring:1.0.1")
}
