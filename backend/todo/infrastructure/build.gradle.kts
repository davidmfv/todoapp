plugins {
    kotlin("jvm") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "dev.hungndl.todo"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2:1.4.200")  // Add this line
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}