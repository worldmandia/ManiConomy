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
            true // TODO
        }
    }

    override fun retrievePlayerAccountIds(): CompletableFuture<MutableCollection<UUID>> {
        return CompletableFuture.supplyAsync {
            mutableSetOf() // TODO
        }
    }

    override fun retrieveNonPlayerAccountIds(): CompletableFuture<MutableCollection<NamespacedKey>> {
        return CompletableFuture.supplyAsync {
            mutableSetOf() // TODO
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

    override fun getCurrencies(): MutableSet<Currency> {
        return utils.currency.toHashSet()
    }

    override fun registerCurrency(currency: Currency): CompletableFuture<TriState> {
        return CompletableFuture.supplyAsync {
            utils.currency.add(currency as TreasuryCurrency)
            TriState.TRUE
        }
    }

    override fun unregisterCurrency(currency: Currency): CompletableFuture<TriState> {
        return CompletableFuture.supplyAsync {
            utils.currency.remove(currency as TreasuryCurrency)
            TriState.TRUE
        }
    }


}