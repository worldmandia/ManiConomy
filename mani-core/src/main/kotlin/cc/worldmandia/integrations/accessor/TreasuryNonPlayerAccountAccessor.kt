package cc.worldmandia.integrations.accessor

import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.integrations.TreasuryUtils
import cc.worldmandia.integrations.account.TreasuryNonPlayerAccount
import me.lokka30.treasury.api.economy.account.NonPlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import java.util.concurrent.CompletableFuture

class TreasuryNonPlayerAccountAccessor(
    private val utils: TreasuryUtils
) : NonPlayerAccountAccessor() {
    override fun getOrCreate(context: NonPlayerAccountCreateContext): CompletableFuture<NonPlayerAccount> {
        return CompletableFuture.supplyAsync {
            if (!utils.bankDataBase.contains("identifier", context.identifier)) {
                utils.bankDataBase.createObject(
                    TreasuryDBBank(
                        0,
                        context.identifier.toString(),
                        context.name,
                        mutableSetOf()
                    )
                )
            }
            TreasuryNonPlayerAccount(context, utils)
        }
    }
}