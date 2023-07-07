plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.0"
    java
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "java")

    group = "cc.worldmandia"
    version = properties["version"]!!

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://repo.mrivanplays.com/repository/maven-all/")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://maven.deltapvp.net/")
    }

    dependencies {
        compileOnly("me.lokka30:treasury-api:2.0.1-SNAPSHOT")
        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }
}