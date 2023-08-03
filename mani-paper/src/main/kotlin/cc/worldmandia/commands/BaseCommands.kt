package cc.worldmandia.commands

import cc.worldmandia.ManiConomyPlugin
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.kyori.adventure.text.Component

class BaseCommands(
    plugin: ManiConomyPlugin
) {
    init {
        commandTree("maniconomy") {
            withAliases("mic")
            withPermission("maniconomy.admin")
            anyExecutor { executor, _ ->
                executor.sendMessage(
                    Component.text(
                        """
                        ManiConomy Help
                        /mic reload
                        /mic admin <set, add, remove> <currency>
                    """.trimIndent()
                    )
                )
            }
        }
        commandTree("balance") {
            withPermission("maniconomy.user.balance")
            withAliases("bal", "money")
            anyExecutor { executor, _ ->
                run {

                }
            }
        }
    }
}