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
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.annotations.NotNull
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.math.BigDecimal


class PaperPlugin : JavaPlugin() {

    var configuration = loadConfig("config.yml", "config.yml")
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
            ), DataBase(dataFolder.path, DataBaseType.H2, TreasuryDBUser::class.java, "testDB", "").dataBaseAPI
                , DataBase(dataFolder.path, DataBaseType.H2, TreasuryDBBank::class.java, "testDBank", "").dataBaseAPI)),
            this.name,
            ServicePriority.NORMAL
        )
    }

    override fun onDisable() {
        CommandAPI.onDisable()
    }

    private fun loadConfig(@NotNull file: String, @NotNull fallbackFile: String): FileConfiguration {
        try {
            val configFile = File(dataFolder, file)
            var defaultConfigStream: InputStream? = getResource(file)
            if (defaultConfigStream == null) {
                //sendToConsole(
                //    Component.text("Could not find $file in jar! Falling back to $fallbackFile")
                //        .color(NamedTextColor.YELLOW)
                //)
                defaultConfigStream = getResource(fallbackFile)
            }
            val configuration: YamlConfiguration = YamlConfiguration.loadConfiguration(configFile)
            if (defaultConfigStream != null) {
                configuration.setDefaults(
                    YamlConfiguration.loadConfiguration(
                        InputStreamReader(
                            defaultConfigStream,
                            Charsets.UTF_8
                        )
                    )
                )
            }
            configuration.options().copyDefaults(true)
            configuration.save(configFile)
            return configuration
        } catch (e: Exception) {
            //sendToConsole(
            //    Component.text("Error occurred while loading config file: $file \n $e").color(NamedTextColor.YELLOW)
            //)
        }
        return YamlConfiguration()
    }

}