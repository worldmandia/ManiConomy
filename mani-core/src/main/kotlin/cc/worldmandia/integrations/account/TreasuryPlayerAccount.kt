package cc.worldmandia.integrations.account

import cc.worldmandia.integrations.TreasuryUtils
import me.lokka30.treasury.api.economy.account.PlayerAccount
import me.lokka30.treasury.api.economy.account.accessor.PlayerAccountAccessor
import me.lokka30.treasury.api.economy.currency.Currency
import me.lokka30.treasury.api.economy.transaction.EconomyTransaction
import me.lokka30.treasury.api.economy.transaction.EconomyTransactionType
import java.math.BigDecimal
import java.time.temporal.Temporal
import java.util.*
import java.util.concurrent.CompletableFuture

class TreasuryPlayerAccount(
    private val context: PlayerAccountAccessor.PlayerAccountCreateContext,
    private val utils: TreasuryUtils
) : PlayerAccount {
    override fun getName(): Optional<String> {
        return Optional.ofNullable(utils.userDataBase.getObject("uuid", context.uniqueId.toString())?.name)
    }

    override fun retrieveBalance(currency: Currency): CompletableFuture<BigDecimal> {
        return CompletableFuture.supplyAsync {
            BigDecimal(
                utils.userDataBase.getObject(
                    "uuid",
                    context.uniqueId.toString()
                )?.currency?.first { it.currencyId == currency.identifier }?.balance ?: "-1"
            )
        }
    }

    override fun doTransaction(economyTransaction: EconomyTransaction): CompletableFuture<BigDecimal> {
        return CompletableFuture.supplyAsync {
            val user = utils.userDataBase.getObject("uuid", context.uniqueId.toString())
            if (user != null) {
                val currency = user.currency.firstOrNull { it.currencyId == economyTransaction.currencyId }
                if (currency != null) {
                    val currentBalance = BigDecimal(currency.balance)
                    val newBalance = when (economyTransaction.type) {
                        EconomyTransactionType.WITHDRAWAL -> currentBalance.subtract(economyTransaction.amount)
                        EconomyTransactionType.DEPOSIT -> currentBalance.add(economyTransaction.amount)
                        EconomyTransactionType.SET -> economyTransaction.amount
                    }

                    currency.balance = newBalance.toPlainString()
                    utils.userDataBase.replaceObject("uuid", context.uniqueId.toString(), user)
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
            utils.userDataBase.removeObject("uuid", context.uniqueId.toString())
        }
    }

    override fun retrieveHeldCurrencies(): CompletableFuture<MutableCollection<String>> {
        return CompletableFuture.supplyAsync {
            utils.userDataBase.getObject("uuid", context.uniqueId.toString())?.currency?.map { it.currencyId }
                ?.toMutableList() ?: mutableListOf()
        }
    }

    override fun retrieveTransactionHistory(
        transactionCount: Int,
        from: Temporal,
        to: Temporal
    ): CompletableFuture<MutableCollection<EconomyTransaction>> {
        TODO("Not yet implemented")
    }

    override fun identifier(): UUID {
        return context.uniqueId
    }
}