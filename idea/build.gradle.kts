group = rootProject.group
version = rootProject.version

plugins {
    id("org.jetbrains.intellij") version "1.3.1" apply true
}

dependencies {
    implementation(project(":core")) {
        exclude("org.slf4j")
    }
    implementation(project(":plugins:pyinjector")) {
        exclude("org.slf4j")
    }
    implementation(project(":plugins:python")) {
        exclude("org.slf4j")
    }
    implementation(project(":plugins:docker")) {
        exclude("org.slf4j")
    }
    implementation(project(":plugins:ssh")) {
        exclude("org.slf4j")
    }
}

intellij {
    pluginName.set("Paddle")

    type.set("PC")
    version.set("PC-2021.1.3")

    downloadSources.set(true)

    plugins.set(
        listOf(
            "PythonCore:211.7628.24",
            "org.jetbrains.plugins.yaml:211.7142.37"
        )
    )

    // updateSinceUntilBuild.set(false)
}

tasks {
    runIde {
        jvmArgs = listOf("-Xmx1024m")
    }
}
