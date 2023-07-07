package cc.worldmandia

import cc.worldmandia.commands.BaseCommands
import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseType
import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.database.objects.TreasuryDBUser
import cc.worldmandia.integrations.TreasuryProvider
import cc.worldmandia.integrations.TreasuryUtils
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Dependency
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIVelocityConfig
import me.lokka30.treasury.api.common.service.ServicePriority
import me.lokka30.treasury.api.common.service.ServiceRegistry
import me.lokka30.treasury.api.economy.EconomyProvider
import net.byteflux.libby.Library
import net.byteflux.libby.VelocityLibraryManager
import org.slf4j.Logger
import java.nio.file.Path


@Plugin(
    id = "maniconomy", name = "ManiConomy velocity", version = "0.0.1-dev",
    url = "https://discord.worldmandia.cc", description = "Economy provider plugin", authors = ["mani1232"],
    dependencies = [
        Dependency(id = "treasury", optional = false)
    ]
)
class VelocityPlugin {
    private lateinit var server: ProxyServer
    private lateinit var logger: Logger
    private lateinit var dataDirectory: Path

    @Inject
    fun VelocityPlugin(server: ProxyServer, logger: Logger, @DataDirectory dataDirectory: Path) {
        this.server = server
        this.logger = logger
        this.dataDirectory = dataDirectory
        logger.info("Starting ManiConomy on velocity")
        val velocityLibraryManager = VelocityLibraryManager(logger, dataDirectory, server.pluginManager, server)
        velocityLibraryManager.addMavenCentral()
        loadLibs(velocityLibraryManager)
    }

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        CommandAPI.onLoad(CommandAPIVelocityConfig(server))
        BaseCommands(this)

        ServiceRegistry.INSTANCE.registerService(
            EconomyProvider::class.java,
            TreasuryProvider(
                TreasuryUtils(
                    mutableSetOf(
                        // TODO From config Currencies
                    ),
                    DataBase(
                        "jdbc:h2:file:",
                        "",
                        "",
                        "",
                        DataBaseType.SQL,
                        TreasuryDBUser::class.java,
                        "",
                        ""
                    ).dataBaseAPI,
                    DataBase(
                        "jdbc:h2:file:",
                        "",
                        "",
                        "",
                        DataBaseType.SQL,
                        TreasuryDBBank::class.java,
                        "",
                        ""
                    ).dataBaseAPI
                )
            ),
            "ManiConomy velocity",
            ServicePriority.NORMAL
        )
        logger.info("Registered Economy Provider")
        //server.eventManager.register(this, listener) register event
    }

    @Subscribe
    fun onProxyShutdown(event: ProxyShutdownEvent) {
        CommandAPI.onDisable()
    }

    private fun <T> loadLibs(velocityLibraryManager: VelocityLibraryManager<T>) {
        velocityLibraryManager.loadLibrary(
            Library.builder()
                .groupId("com{}h2database")
                .artifactId("h2")
                .version("2.1.214")
                .build()
        )
        velocityLibraryManager.loadLibrary(
            Library.builder()
                .groupId("org{}mariadb{}jdbc")
                .artifactId("mariadb-java-client")
                .version("3.1.4")
                .build()
        )
        velocityLibraryManager.loadLibrary(
            Library.builder()
                .groupId("org{}jetbrains{}kotlinx")
                .artifactId("kotlinx-serialization-json-jvm")
                .version("1.5.1")
                .build()
        )
        velocityLibraryManager.loadLibrary(
            Library.builder()
                .groupId("org{}litote{}kmongo")
                .artifactId("kmongo-coroutine-serialization")
                .version("4.9.0")
                .build()
        )
        velocityLibraryManager.loadLibrary(
            Library.builder()
                .groupId("org{}mongodb")
                .artifactId("mongodb-driver-sync")
                .version("4.10.1")
                .build()
        )
    }
}