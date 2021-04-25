plugins {
    val ktVersion = "1.5.0-RC"

    // Android
    id("com.android.application") apply false
    id("com.android.library") apply false

    // Kotlin
    kotlin("jvm") version ktVersion apply false
    kotlin("android") version ktVersion apply false
    kotlin("plugin.spring") version ktVersion apply false

    // Menkalian Plugins
    id("de.menkalian.vela.versioning") version "1.1.0" apply false
    id("de.menkalian.vela.buildconfig") version "1.0.0" apply false
}

allprojects {
    group = "de.menkalian.apus"

    repositories {
        mavenCentral()
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.plugin.spring") {
        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        dependencies {
            add("implementation", kotlin("reflect"))
            add("implementation", kotlin("stdlib"))
        }
    }

    pluginManager.withPlugin("maven-publish") {
        extensions.getByType(PublishingExtension::class.java).apply {
            repositories {
                maven {
                    url = uri("http://server.menkalian.de:8081/artifactory/apus")
                    name = "artifactory-menkalian"
                    credentials {
                        username = System.getenv("MAVEN_REPO_USER")
                        password = System.getenv("MAVEN_REPO_PASS")
                    }
                }
            }
        }

        pluginManager.withPlugin("de.menkalian.vela.versioning") {
            extensions.getByType(de.menkalian.vela.gradle.VersioningExtension::class).apply {
                upgradeTask = tasks.getByName("publish")
            }
        }
    }

    pluginManager.withPlugin("java") {
        extensions.getByType(JavaPluginExtension::class).apply {
            withJavadocJar()
            withSourcesJar()
        }

        dependencies {
            add("testImplementation", "org.junit.jupiter:junit-jupiter-api:5.7.0")
            add("testImplementation", "org.junit.jupiter:junit-jupiter-params:5.7.0")
            add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine:5.7.0")
        }

        tasks {
            withType(Test::class.java) {
                useJUnitPlatform()
            }
        }
    }
}
