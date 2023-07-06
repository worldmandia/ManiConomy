package cc.worldmandia.database.objects

import me.lokka30.treasury.api.economy.transaction.EconomyTransactionImportance
import me.lokka30.treasury.api.economy.transaction.EconomyTransactionType

class TreasuryDBTransactionHistory(
    amount: String,
    currencyId: String,
    type: EconomyTransactionType,
    cause: String,
    reason: String?,
    importance: EconomyTransactionImportance
)