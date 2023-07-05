package cc.worldmandia.commands

import cc.worldmandia.PaperPlugin
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.kyori.adventure.text.Component

class BaseCommands(
    plugin: PaperPlugin
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