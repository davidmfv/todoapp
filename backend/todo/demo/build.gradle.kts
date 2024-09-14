plugins {
    kotlin("jvm") version "1.9.25"
    application
}

group = "dev.hungndl.todo"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":notion-client"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("ch.qos.logback:logback-classic:1.4.11")
}

application {
    mainClass.set("dev.hungndl.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}