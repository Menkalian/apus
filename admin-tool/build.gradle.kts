import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Spring
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    // Kotlin
    kotlin("jvm")
    kotlin("plugin.spring")

    // General
    java
    `maven-publish`

    // Menkalian
    id("de.menkalian.vela.versioning")
    id("de.menkalian.vela.buildconfig")
}

version = "0.0.1_${versioning.buildNo}"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
