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
    var amount: String = "",
    var currencyId: String = "",
    var type: EconomyTransactionType? = null,
    var cause: String = "",
    var reason: String? = "",
    var importance: EconomyTransactionImportance? = null
) {
    override fun toString(): String {
        return "TreasuryDBTransactionHistory(objectId=$objectId, amount='$amount', currencyId='$currencyId', type=$type, cause='$cause', reason=$reason, importance=$importance)"
    }
}