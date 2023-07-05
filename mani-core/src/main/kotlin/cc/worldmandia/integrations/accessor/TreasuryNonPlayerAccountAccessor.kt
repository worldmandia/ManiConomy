package cc.worldmandia.integrations.accessor

import cc.worldmandia.integrations.account.TreasuryNonPlayerAccount
import me.lokka30.treasury.api.economy.account.NonPlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import java.util.concurrent.CompletableFuture

class TreasuryNonPlayerAccountAccessor : NonPlayerAccountAccessor() {
    override fun getOrCreate(context: NonPlayerAccountCreateContext): CompletableFuture<NonPlayerAccount> {
        return CompletableFuture<NonPlayerAccount>().completeAsync {
            TreasuryNonPlayerAccount(context)
        }
    }
}