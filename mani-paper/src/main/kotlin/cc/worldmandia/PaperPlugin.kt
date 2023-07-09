package cc.worldmandia

import cc.worldmandia.commands.BaseCommands
import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseAPI
import cc.worldmandia.database.DataBaseType
import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.database.objects.TreasuryDBTransactionHistory
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

    private lateinit var configuration: FileConfiguration
    var currencies = loadCustomConfig("currencies.yml", "currencies.yml")
    var lang = loadCustomConfig("lang.yml", "lang.yml")
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this))
        saveDefaultConfig()
    }

    override fun onEnable() {
        BaseCommands(this)
        configuration = config

        var transactionDataBase: DataBaseAPI<TreasuryDBTransactionHistory>? = null
        if (config.getBoolean("transaction-history-enable", false)) {
            transactionDataBase = DataBase(
                    config.getString("transaction-history-db-url", "jdbc:h2:file:")!!,
                    dataFolder.path,
                    config.getString("transaction-history-db-user", "")!!,
                    config.getString("transaction-history-db-pass", "")!!,
                    DataBaseType.valueOf(config.getString("player-db-type", "SQL")!!.uppercase()),
                    TreasuryDBTransactionHistory::class.java,
                    config.getString("transaction-history-db-name", "playerDB")!!,
                    config.getString("transaction-history-db-collection", "playerCollection")!!
            ).dataBaseAPI
        }

        ServiceRegistry.INSTANCE.registerService(
                EconomyProvider::class.java,
                TreasuryProvider(
                        TreasuryUtils(
                                mutableSetOf(
                                        TreasuryCurrency(
                                                "test",
                                                true,
                                                mutableMapOf(),
                                                BigDecimal(100),
                                                3,
                                                BigDecimal(0.5),
                                                "$"
                                        ) // for test
                                        // TODO From config Currencies
                                ),
                                DataBase(
                                        config.getString("player-db-url", "jdbc:h2:file:")!!,
                                        dataFolder.path,
                                        config.getString("player-db-user", "")!!,
                                        config.getString("player-db-pass", "")!!,
                                        DataBaseType.valueOf(config.getString("player-db-type", "SQL")!!.uppercase()),
                                        TreasuryDBUser::class.java,
                                        config.getString("player-db-name", "playerDB")!!,
                                        config.getString("player-db-collection", "playerCollection")!!
                                ).dataBaseAPI,
                                DataBase(
                                        config.getString("non-player-db-url", "jdbc:h2:file:")!!,
                                        dataFolder.path,
                                        config.getString("non-player-db-user", "")!!,
                                        config.getString("non-player-db-pass", "")!!,
                                        DataBaseType.valueOf(config.getString("player-db-type", "SQL")!!.uppercase()),
                                        TreasuryDBBank::class.java,
                                        config.getString("non-player-db-name", "playerDB")!!,
                                        config.getString("non-player-db-collection", "playerCollection")!!
                                ).dataBaseAPI,
                                transactionDataBase
                        )
                ),
                this.name,
                ServicePriority.NORMAL
        )
    }

    override fun onDisable() {
        CommandAPI.onDisable()
    }

    private fun loadCustomConfig(@NotNull file: String, @NotNull fallbackFile: String): FileConfiguration {
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