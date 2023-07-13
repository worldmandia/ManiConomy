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
        return Optional.ofNullable(utils.bankDataBase.getObject("identifier", context.identifier)?.name)
    }

    override fun setName(name: String?): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val account = utils.bankDataBase.getObject("identifier", context.identifier)
            if (account != null) {
                utils.bankDataBase.replaceObject("identifier", context.identifier, account)
            } else {
                false
            }
        }
    }

    override fun retrieveBalance(currency: Currency): CompletableFuture<BigDecimal> {
        return CompletableFuture.supplyAsync {
            BigDecimal(
                utils.userDataBase.getObject(
                    "identifier",
                    context.identifier.toString()
                )?.currency?.first { it.currencyId == currency.identifier }?.balance ?: "-1"
            )
        }
    }

    override fun doTransaction(economyTransaction: EconomyTransaction): CompletableFuture<BigDecimal> {
        return CompletableFuture.supplyAsync {
            val bank = utils.bankDataBase.getObject("identifier", context.identifier.toString())
            if (bank != null) {
                val currencyToUpdate = bank.currency.firstOrNull { it.currencyId == economyTransaction.currencyId }
                if (currencyToUpdate != null) {
                    val currentBalance = BigDecimal(currencyToUpdate.balance)
                    val newBalance = when (economyTransaction.type) {
                        EconomyTransactionType.WITHDRAWAL -> currentBalance.subtract(economyTransaction.amount)
                        EconomyTransactionType.DEPOSIT -> currentBalance.add(economyTransaction.amount)
                        EconomyTransactionType.SET -> economyTransaction.amount
                    }

                    bank.currency.remove(currencyToUpdate)
                    currencyToUpdate.balance = newBalance.toPlainString()
                    bank.currency.add(currencyToUpdate)

                    utils.bankDataBase.replaceObject("identifier", context.identifier.toString(), bank)
                    newBalance
                } else {
                    BigDecimal(-1)
                }
            } else {
                BigDecimal(-1)
            }
        }
    }

    override fun deleteAccount(): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            utils.bankDataBase.removeObject("identifier", context.identifier.toString())
        }
    }

    override fun retrieveHeldCurrencies(): CompletableFuture<MutableCollection<String>> {
        return CompletableFuture.supplyAsync {
            utils.bankDataBase.getObject("identifier", context.identifier.toString())?.currency?.map { it.currencyId }
                    ?.toMutableList() ?: mutableListOf()
        }
    }

    override fun retrieveTransactionHistory(
        transactionCount: Int,
        from: Temporal,
        to: Temporal
    ): CompletableFuture<MutableCollection<EconomyTransaction>> {
        return CompletableFuture.supplyAsync {
            if (utils.transactionDataBase == null) {
                mutableListOf<EconomyTransaction>()
            }
            mutableListOf()
        }
    }

    override fun retrieveMemberIds(): CompletableFuture<MutableCollection<UUID>> {
        return CompletableFuture.supplyAsync {
            utils.bankDataBase.getObject("identifier", context.identifier)?.members?.map { UUID.fromString(it.uuid) }
                ?.toMutableSet() ?: mutableSetOf()
        }
    }

    override fun isMember(player: UUID): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val bank = utils.bankDataBase.getObject("identifier", context.identifier)
            bank?.members?.first { it.uuid == player.toString() }

            false
        }
    }

    override fun setPermissions(
        player: UUID,
        permissionsMap: MutableMap<AccountPermission, TriState>
    ): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val accountPermissions = utils.bankDataBase.getObject("identifier", context.identifier)?.members?.first { it.uuid == player.toString() }?.accountPermissions!!.first { it.bankId == context.identifier.toString() }
            permissionsMap.forEach {
                // TODO
            }
            false
        }
    }

    override fun retrievePermissions(player: UUID): CompletableFuture<MutableMap<AccountPermission, TriState>> {
        return CompletableFuture.supplyAsync { // TODO planned
            mutableMapOf(
                Pair(AccountPermission.MODIFY_PERMISSIONS, TriState.TRUE),
                Pair(AccountPermission.DEPOSIT, TriState.TRUE),
                Pair(AccountPermission.WITHDRAW, TriState.TRUE),
                Pair(AccountPermission.BALANCE, TriState.TRUE)
            )
        }
    }

    override fun retrievePermissionsMap(): CompletableFuture<MutableMap<UUID, MutableMap<AccountPermission, TriState>>> {
        return CompletableFuture.supplyAsync { // TODO planned
            mutableMapOf()
        }
    }

    override fun hasPermissions(player: UUID, vararg permissions: AccountPermission): CompletableFuture<TriState> {
        return CompletableFuture.supplyAsync { // TODO planned
            TriState.TRUE
        }
    }

    override fun identifier(): NamespacedKey {
        return context.identifier
    }

}