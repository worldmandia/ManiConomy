package cc.worldmandia.database.objects

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@AllArgsConstructor
@NoArgsConstructor
class TreasuryDBCurrency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: String = "",
    var id: String = "",
    var balance: String = "-1"
)