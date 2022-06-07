group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":plugins:python"))
    implementation(kotlin("stdlib"))
}
