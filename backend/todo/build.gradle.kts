plugins {
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.3.3" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

group = "dev.hungndl.todo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}