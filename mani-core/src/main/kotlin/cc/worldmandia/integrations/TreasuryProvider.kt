package cc.worldmandia.integrations

import cc.worldmandia.integrations.accessor.TreasuryAccessor
import me.lokka30.treasury.api.common.NamespacedKey
import me.lokka30.treasury.api.common.misc.TriState
import me.lokka30.treasury.api.economy.EconomyProvider
import me.lokka30.treasury.api.economy.account.AccountData
import me.lokka30.treasury.api.economy.account.accessor.AccountAccessor
import me.lokka30.treasury.api.economy.currency.Currency
import java.util.*
import java.util.concurrent.CompletableFuture

class TreasuryProvider(
    private val utils: TreasuryUtils
) : EconomyProvider {
    override fun accountAccessor(): AccountAccessor {
        return TreasuryAccessor(utils)
    }

    override fun hasAccount(accountData: AccountData): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            if (accountData.isPlayerAccount) {
                utils.userDataBase.contains("uuid", accountData.playerIdentifier.get().toString())
            } else {
                utils.bankDataBase.contains("identifier", accountData.nonPlayerIdentifier.get().toString())
            }
        }
    }

    override fun retrievePlayerAccountIds(): CompletableFuture<MutableCollection<UUID>> {
        return CompletableFuture.supplyAsync {
            utils.userDataBase.getAllObjects().map { UUID.fromString(it.uuid) }.toMutableSet()
        }
    }

    override fun retrieveNonPlayerAccountIds(): CompletableFuture<MutableCollection<NamespacedKey>> {
        return CompletableFuture.supplyAsync {
            utils.bankDataBase.getAllObjects().map { NamespacedKey.fromString(it.identifier) }.toMutableSet()
        }
    }

    override fun getPrimaryCurrency(): Currency {
        return utils.currency.first { it.isPrimary }
    }

    override fun findCurrency(identifier: String): Optional<Currency> {
        return utils.currency.stream()
            .filter { it.identifier == identifier }
            .findFirst().map { it as Currency }
    }

    override fun getCurrencies(): MutableSet<TreasuryCurrency> {
        return utils.currency
    }

    override fun registerCurrency(currency: Currency): CompletableFuture<TriState> {
        return CompletableFuture.supplyAsync {
            utils.currency.add(currency as TreasuryCurrency)
            TriState.TRUE
        }
    }

    override fun unregisterCurrency(currency: Currency): CompletableFuture<TriState> {
        return CompletableFuture.supplyAsync {
            val currencyOpt = utils.currency.stream()
                    .filter { it.identifier == currency.identifier }
                    .findFirst()
            if (currencyOpt.isPresent) {
                utils.currency.remove(currencyOpt.get())
                TriState.TRUE
            } else {
                TriState.FALSE
            }
        }
    }


}