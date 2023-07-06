package cc.worldmandia.database.objects

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import me.lokka30.treasury.api.economy.transaction.EconomyTransactionImportance
import me.lokka30.treasury.api.economy.transaction.EconomyTransactionType

@AllArgsConstructor
@NoArgsConstructor
@Entity
class TreasuryDBTransactionHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: Long = 0,
    amount: String = "",
    currencyId: String = "",
    type: EconomyTransactionType? = null,
    cause: String = "",
    reason: String? = "",
    importance: EconomyTransactionImportance? = null
)