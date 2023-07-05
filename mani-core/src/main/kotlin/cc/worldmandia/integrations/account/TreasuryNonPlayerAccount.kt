package cc.worldmandia.integrations.account

import me.lokka30.treasury.api.common.NamespacedKey
import me.lokka30.treasury.api.common.misc.TriState
import me.lokka30.treasury.api.economy.account.AccountPermission
import me.lokka30.treasury.api.economy.account.NonPlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import me.lokka30.treasury.api.economy.currency.Currency
import me.lokka30.treasury.api.economy.transaction.EconomyTransaction
import java.math.BigDecimal
import java.time.temporal.Temporal
import java.util.*
import java.util.concurrent.CompletableFuture

class TreasuryNonPlayerAccount(
    private val context: NonPlayerAccountAccessor.NonPlayerAccountCreateContext
) : NonPlayerAccount {
    override fun getName(): Optional<String> {
        TODO("Not yet implemented")
    }

    override fun setName(name: String?): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

    override fun retrieveBalance(currency: Currency): CompletableFuture<BigDecimal> {
        TODO("Not yet implemented")
    }

    override fun doTransaction(economyTransaction: EconomyTransaction): CompletableFuture<BigDecimal> {
        TODO("Not yet implemented")
    }

    override fun deleteAccount(): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

    override fun retrieveHeldCurrencies(): CompletableFuture<MutableCollection<String>> {
        TODO("Not yet implemented")
    }

    override fun retrieveTransactionHistory(
        transactionCount: Int,
        from: Temporal,
        to: Temporal
    ): CompletableFuture<MutableCollection<EconomyTransaction>> {
        TODO("Not yet implemented")
    }

    override fun retrieveMemberIds(): CompletableFuture<MutableCollection<UUID>> {
        TODO("Not yet implemented")
    }

    override fun isMember(player: UUID): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

    override fun setPermissions(
        player: UUID,
        permissionsMap: MutableMap<AccountPermission, TriState>
    ): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

    override fun retrievePermissions(player: UUID): CompletableFuture<MutableMap<AccountPermission, TriState>> {
        TODO("Not yet implemented")
    }

    override fun retrievePermissionsMap(): CompletableFuture<MutableMap<UUID, MutableMap<AccountPermission, TriState>>> {
        TODO("Not yet implemented")
    }

    override fun hasPermissions(player: UUID, vararg permissions: AccountPermission): CompletableFuture<TriState> {
        TODO("Not yet implemented")
    }

    override fun identifier(): NamespacedKey {
        return context.identifier
    }

}