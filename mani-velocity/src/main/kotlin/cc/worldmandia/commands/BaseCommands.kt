package cc.worldmandia.commands

import cc.worldmandia.VelocityPlugin
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.kyori.adventure.text.Component

class BaseCommands(
    plugin: VelocityPlugin
) {
    init {
        commandTree("maniconomy") {
            withAliases("mic")
            withPermission("maniconomy.admin")
            anyExecutor { executor, _ ->
                executor.sendMessage(Component.text(
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