package cc.worldmandia

import cc.worldmandia.commands.BaseCommands
import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseType
import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.database.objects.TreasuryDBUser
import cc.worldmandia.integrations.TreasuryCurrency
import cc.worldmandia.integrations.TreasuryProvider
import cc.worldmandia.integrations.TreasuryUtils
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import me.lokka30.treasury.api.common.service.ServicePriority
import me.lokka30.treasury.api.common.service.ServiceRegistry
import me.lokka30.treasury.api.economy.EconomyProvider
import org.bukkit.plugin.java.JavaPlugin
import java.math.BigDecimal


class PaperPlugin : JavaPlugin() {

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this))
    }

    override fun onEnable() {
        BaseCommands(this)

        ServiceRegistry.INSTANCE.registerService(
            EconomyProvider::class.java,
            TreasuryProvider(TreasuryUtils(mutableSetOf(
                TreasuryCurrency("test", true, mutableMapOf(), BigDecimal(100), 3, BigDecimal(0.5), "$") // for test
                // TODO From config Currencies
            ), DataBase("", DataBaseType.MONGO, TreasuryDBUser::class.java, "", "").dataBaseAPI
                , DataBase("", DataBaseType.MONGO, TreasuryDBBank::class.java, "", "").dataBaseAPI)),
            this.name,
            ServicePriority.NORMAL
        )
    }

    override fun onDisable() {

    }

}