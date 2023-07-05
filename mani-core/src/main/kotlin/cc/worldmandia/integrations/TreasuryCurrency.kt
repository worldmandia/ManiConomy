package cc.worldmandia.integrations

import me.lokka30.treasury.api.economy.account.Account
import me.lokka30.treasury.api.economy.currency.Currency
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.CompletableFuture

class TreasuryCurrency(
    private val identifier: String,
    private val primary: Boolean,
    private val localeDecimal: MutableMap<Locale, Char>,
    private val startBalance: BigDecimal,
    private val precision: Int,
    private val conversionRate: BigDecimal,
    private val symbol: String
) : Currency {
    override fun getIdentifier(): String {
        return identifier
    }

    override fun getSymbol(): String {
        return symbol
    }

    override fun getDecimal(locale: Locale?): Char {
        return localeDecimal.getOrDefault(locale, '$')
    }

    override fun getLocaleDecimalMap(): MutableMap<Locale, Char> {
        return localeDecimal
    }

    override fun getDisplayName(value: BigDecimal, locale: Locale?): String {
        return value.setScale(precision).toString()
    }

    override fun getPrecision(): Int {
        return precision
    }

    override fun isPrimary(): Boolean {
        return primary
    }

    override fun getStartingBalance(account: Account): BigDecimal {
        return startBalance
    }

    override fun getConversionRate(): BigDecimal {
        return conversionRate
    }

    override fun parse(formattedAmount: String, locale: Locale?): CompletableFuture<BigDecimal> {
        return CompletableFuture<BigDecimal>().completeAsync {
            BigDecimal(formattedAmount.replace(localeDecimalMap.getOrDefault(locale, '$'), ' ', true).trim())
        }
    }

    override fun format(amount: BigDecimal, locale: Locale?): String {
        return amount.toString() + localeDecimalMap.getOrDefault(locale, '$')
    }

    override fun format(amount: BigDecimal, locale: Locale?, precision: Int): String {
        return amount.setScale(precision).toString() + localeDecimalMap.getOrDefault(locale, '$')
    }
}