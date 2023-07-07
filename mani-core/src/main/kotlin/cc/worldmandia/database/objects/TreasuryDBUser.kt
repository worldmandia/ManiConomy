package cc.worldmandia.database.objects

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Entity
class TreasuryDBUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: Long = 0,
    var name: String = "",
    var uuid: String = "",
    @OneToMany(cascade = [CascadeType.ALL])
    var currency: MutableSet<TreasuryDBCurrency> = mutableSetOf()
) {
    override fun toString(): String {
        return "TreasuryDBUser(objectId=$objectId, name=$name, uuid=$uuid, currency=$currency)"
    }
}