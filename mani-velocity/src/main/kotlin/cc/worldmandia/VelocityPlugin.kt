package cc.worldmandia

import cc.worldmandia.commands.BaseCommands
import cc.worldmandia.integrations.TreasuryProvider
import cc.worldmandia.integrations.TreasuryUtils
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Dependency
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import me.lokka30.treasury.api.common.service.ServicePriority
import me.lokka30.treasury.api.common.service.ServiceRegistry
import me.lokka30.treasury.api.economy.EconomyProvider
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
    }

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        BaseCommands(server)

        ServiceRegistry.INSTANCE.registerService(
            EconomyProvider::class.java,
            TreasuryProvider(TreasuryUtils(mutableSetOf(
                // TODO From config Currencies
            ))),
            "ManiConomy velocity",
            ServicePriority.NORMAL
        )
        //server.eventManager.register(this, listener) register event
    }
}