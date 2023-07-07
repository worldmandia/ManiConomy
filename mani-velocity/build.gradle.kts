import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val pluginName = "${properties["plugin-name"]}-velocity"

plugins {
    kotlin("kapt")
}

dependencies {
    implementation(project(":mani-core", configuration = "shadow"))
    compileOnly("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
    implementation("dev.jorel:commandapi-velocity-shade:9.0.4-SNAPSHOT")
    implementation("dev.jorel:commandapi-velocity-kotlin:9.0.4-SNAPSHOT")
    implementation("net.deltapvp.libby:libby-velocity:2.0.4-SNAPSHOT")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            kotlinOptions.jvmTarget = "17"
            archivesName.set(pluginName)
        }
    }
    withType<Jar> {
        destinationDirectory = file("$rootDir/bin")
    }

}

tasks.shadowJar {
    relocate("dev.jorel.commandapi", "cc.worldmandia.commandapi")
}

kapt {
    keepJavacAnnotationProcessors = true
}