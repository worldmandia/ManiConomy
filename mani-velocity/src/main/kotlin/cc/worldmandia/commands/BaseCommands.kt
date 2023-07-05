package cc.worldmandia.commands

import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.ProxyServer
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.kyori.adventure.text.Component

class BaseCommands(
    plugin: ProxyServer
) {
    init {
        commandTree("maniconomy") {
            withAliases("mic")
            withPermission("maniconomy.admin")
            anyExecutor { _, args ->
                val commandSource: CommandSource = args[0] as CommandSource
                commandSource.sendMessage(Component.text(
                    """
                        ManiConomy Help
                        /mic reload
                        /mic admin <set, add, remove> <currency>
                    """.trimIndent()
                ))
            }
        }
    }
}