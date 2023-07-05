package cc.worldmandia

import cc.worldmandia.commands.BaseCommands
import cc.worldmandia.integrations.TreasuryProvider
import cc.worldmandia.integrations.TreasuryUtils
import me.lokka30.treasury.api.common.service.ServicePriority
import me.lokka30.treasury.api.common.service.ServiceRegistry
import me.lokka30.treasury.api.economy.EconomyProvider
import org.bukkit.plugin.java.JavaPlugin


class PaperPlugin : JavaPlugin() {

    override fun onLoad() {

    }

    override fun onEnable() {
        BaseCommands(this)

        ServiceRegistry.INSTANCE.registerService(
            EconomyProvider::class.java,
            TreasuryProvider(TreasuryUtils(mutableSetOf(
                // TODO From config Currencies
            ))),
            this.name,
            ServicePriority.NORMAL
        )
    }

    override fun onDisable() {

    }

}