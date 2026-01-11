import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    kotlin("jvm") version "2.3.0" apply false
    id("org.jetbrains.dokka") version "2.1.0" apply false
    id("com.vanniktech.maven.publish") version "0.35.0" apply false
    id("pl.allegro.tech.build.axion-release") version "1.21.1"
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://libraries.minecraft.net/")
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

scmVersion {
    tag {
        prefix.set("v")
    }
    versionIncrementer("incrementMinor")
}

group = "io.github.kbrigx"
version = scmVersion.version

subprojects {
    group = rootProject.group
    version = rootProject.version
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java-library")
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

    extensions.configure<KotlinJvmProjectExtension> {
        explicitApi()
        jvmToolchain(21)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    extensions.configure<BasePluginExtension> {
        archivesName.set("kbrigx-${project.name}")
    }

    extensions.configure<DokkaExtension>() {
        dokkaSourceSets.configureEach {
            moduleName.set("kbrigx-${project.name}")
            includes.from(file("docs/${project.name}.md"))
        }
    }

    extensions.configure<MavenPublishBaseExtension> {

        publishToMavenCentral()
        signAllPublications()

        coordinates(project.group.toString(), "kbrigx-${project.name}", project.version.toString())

        pom {
            name.set("kbrigx-${project.name}")
            description.set(
                when (project.name) {
                    "core" -> "Kotlin DSL wrapper for Mojang Brigadier."
                    "paper" -> "Paper integration for kbrigx."
                    else -> "Kotlin DSL wrapper for Mojang Brigadier."
                }
            )
            inceptionYear.set("2026")
            url.set("https://github.com/kbrigx/kbrigx")

            licenses {
                license {
                    name.set("Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }

            developers {
                developer {
                    id.set("minoneer")
                    name.set("minoneer")
                    email.set("minoneer@gmail.com")
                    url.set("https://github.com/minoneer")
                }
            }

            scm {
                url.set("https://github.com/kbrigx/kbrigx")
                connection.set("scm:git:https://github.com/kbrigx/kbrigx.git")
                developerConnection.set("scm:git:ssh://git@github.com:kbrigx/kbrigx.git")
            }

            issueManagement {
                system.set("GitHub Issues")
                url.set("https://github.com/kbrigx/kbrigx/issues")
            }

            ciManagement {
                system.set("GitHub Actions")
                url.set("https://github.com/kbrigx/kbrigx/actions")
            }

            organization {
                name.set("kbrigx")
                url.set("https://github.com/kbrigx")
            }
        }
    }
}
