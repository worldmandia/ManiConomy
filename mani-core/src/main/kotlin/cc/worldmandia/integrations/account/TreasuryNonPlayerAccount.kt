package cc.worldmandia.integrations.account

import cc.worldmandia.integrations.TreasuryUtils
import me.lokka30.treasury.api.common.NamespacedKey
import me.lokka30.treasury.api.common.misc.TriState
import me.lokka30.treasury.api.economy.account.AccountPermission
import me.lokka30.treasury.api.economy.account.NonPlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import me.lokka30.treasury.api.economy.currency.Currency
import me.lokka30.treasury.api.economy.transaction.EconomyTransaction
import me.lokka30.treasury.api.economy.transaction.EconomyTransactionType
import java.math.BigDecimal
import java.time.temporal.Temporal
import java.util.*
import java.util.concurrent.CompletableFuture

class TreasuryNonPlayerAccount(
    private val context: NonPlayerAccountAccessor.NonPlayerAccountCreateContext,
    private val utils: TreasuryUtils
) : NonPlayerAccount {
    override fun getName(): Optional<String> {
        return Optional.ofNullable(context.name)
    }

    override fun setName(name: String?): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

    override fun retrieveBalance(currency: Currency): CompletableFuture<BigDecimal> {
        return CompletableFuture<BigDecimal>().completeAsync {
            BigDecimal(0) // TODO
        }
    }

    override fun doTransaction(economyTransaction: EconomyTransaction): CompletableFuture<BigDecimal> {
        return CompletableFuture<BigDecimal>().completeAsync {
            when (economyTransaction.type) {
                EconomyTransactionType.WITHDRAWAL -> {
                    BigDecimal(0) // TODO
                }
                EconomyTransactionType.DEPOSIT -> {
                    BigDecimal(0)// TODO
                }
                EconomyTransactionType.SET -> {
                    BigDecimal(0)// TODO
                }
                else -> {
                    BigDecimal(-1)
                }
            }
        }
    }

    override fun deleteAccount(): CompletableFuture<Boolean> {
        return CompletableFuture<Boolean>().completeAsync {
            true // TODO
        }
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