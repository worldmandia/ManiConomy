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
    @Column(columnDefinition = "VARCHAR(255)")
    var currencyId: String? = null,
    @Column(columnDefinition = "VARCHAR(255)")
    var accountId: String? = null,
    @Column(columnDefinition = "VARCHAR(255)")
    var balance: String? = null
)