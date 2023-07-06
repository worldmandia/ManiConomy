package cc.worldmandia.integrations.accessor

import cc.worldmandia.database.objects.TreasuryDBUser
import cc.worldmandia.integrations.TreasuryUtils
import cc.worldmandia.integrations.account.TreasuryPlayerAccount
import me.lokka30.treasury.api.economy.account.PlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.PlayerAccountAccessor
import org.bson.types.ObjectId
import java.util.concurrent.CompletableFuture

class TreasuryPlayerAccountAccessor(
    private val utils: TreasuryUtils
) : PlayerAccountAccessor() {
    override fun getOrCreate(context: PlayerAccountCreateContext): CompletableFuture<PlayerAccount> {
        return CompletableFuture<PlayerAccount>().completeAsync {
            if (!utils.userDataBase.contains("uuid", context.uniqueId.toString())) {
                utils.userDataBase.createObject(
                    TreasuryDBUser(
                        ObjectId.get().toString(),
                        "${context.uniqueId}-maniconomy",
                        context.uniqueId.toString(),
                        "",
                        listOf()
                    )
                )
            }
            TreasuryPlayerAccount(context, utils)
        }
    }
}