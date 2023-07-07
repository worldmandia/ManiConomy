import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val pluginName = "${properties["plugin-name"]}-paper"

dependencies {
    implementation(project(":mani-core", configuration = "shadow"))
    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT")
    implementation("dev.jorel:commandapi-bukkit-shade:9.0.4-SNAPSHOT")
    implementation("dev.jorel:commandapi-bukkit-kotlin:9.0.4-SNAPSHOT")
    compileOnly("net.kyori:adventure-platform-bukkit:4.3.0")
}

tasks {

    val props = mapOf(
        "version" to version,
        "pluginName" to pluginName
    )

    withType<KotlinCompile> {
        kotlinOptions {
            kotlinOptions.jvmTarget = "17"
            archivesName.set(pluginName)

            processResources {
                inputs.properties(props)
                filteringCharset = "UTF-8"

                filesMatching(listOf("**plugin.yml")) {
                    expand(props)
                }
            }
        }
    }
    withType<Jar> {
        dependsOn
        destinationDirectory = file("$rootDir/bin")
    }
}

tasks.shadowJar {
    relocate("dev.jorel.commandapi", "cc.worldmandia.commandapi")
}