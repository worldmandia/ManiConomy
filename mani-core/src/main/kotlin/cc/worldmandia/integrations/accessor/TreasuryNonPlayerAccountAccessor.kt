package cc.worldmandia.integrations.accessor

import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.integrations.TreasuryUtils
import cc.worldmandia.integrations.account.TreasuryNonPlayerAccount
import me.lokka30.treasury.api.economy.account.NonPlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import org.bson.types.ObjectId
import java.util.concurrent.CompletableFuture

class TreasuryNonPlayerAccountAccessor(
    private val utils: TreasuryUtils
) : NonPlayerAccountAccessor() {
    override fun getOrCreate(context: NonPlayerAccountCreateContext): CompletableFuture<NonPlayerAccount> {
        return CompletableFuture<NonPlayerAccount>().completeAsync {
            if (!utils.bankDataBase.contains("identifier", context.identifier)) {
                utils.bankDataBase.createObject(
                    TreasuryDBBank(
                        ObjectId.get().toString(),
                        context.identifier.toString(),
                        context.name
                    )
                )
            }
            TreasuryNonPlayerAccount(context, utils)
        }
    }
}