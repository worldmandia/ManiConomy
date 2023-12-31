package cc.worldmandia.integrations.accessor

import cc.worldmandia.database.objects.TreasuryDBUser
import cc.worldmandia.integrations.TreasuryUtils
import cc.worldmandia.integrations.account.TreasuryPlayerAccount
import me.lokka30.treasury.api.economy.account.PlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.PlayerAccountAccessor
import java.util.concurrent.CompletableFuture

class TreasuryPlayerAccountAccessor(
    private val utils: TreasuryUtils
) : PlayerAccountAccessor() {
    override fun getOrCreate(context: PlayerAccountCreateContext): CompletableFuture<PlayerAccount> {
        return CompletableFuture.supplyAsync {
            if (!utils.userDataBase.contains("uuid", context.uniqueId.toString())) {
                utils.userDataBase.createObject(
                    TreasuryDBUser(
                        0,
                        "${context.uniqueId}-maniconomy",
                        context.uniqueId.toString(),
                        mutableSetOf()
                    )
                )
            }
            TreasuryPlayerAccount(context, utils)
        }
    }
}