package cc.worldmandia.integrations.accessor

import cc.worldmandia.integrations.account.TreasuryPlayerAccount
import me.lokka30.treasury.api.economy.account.PlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.PlayerAccountAccessor
import java.util.concurrent.CompletableFuture

class TreasuryPlayerAccountAccessor : PlayerAccountAccessor() {
    override fun getOrCreate(context: PlayerAccountCreateContext): CompletableFuture<PlayerAccount> {
        return CompletableFuture<PlayerAccount>().completeAsync {
            TreasuryPlayerAccount()
        }
    }
}