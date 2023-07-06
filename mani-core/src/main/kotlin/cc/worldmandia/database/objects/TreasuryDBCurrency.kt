package cc.worldmandia.database.objects

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Entity
class TreasuryDBCurrency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: Long = 0,
    var currencyId: String = "",
    var accountId: String = "",
    var balance: String = ""
) {
    override fun toString(): String {
        return "TreasuryDBCurrency(objectId=$objectId, currencyId=$currencyId, accountId=$accountId, balance=$balance)"
    }
}