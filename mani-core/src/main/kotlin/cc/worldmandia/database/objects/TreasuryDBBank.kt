package cc.worldmandia.database.objects

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Entity
class TreasuryDBBank(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: Long = 0,
    var identifier: String = "",
    var name: String? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    var currency: MutableSet<TreasuryDBCurrency> = mutableSetOf(),
    @OneToMany(cascade = [CascadeType.ALL])
    var members: MutableSet<TreasuryDBMember> = mutableSetOf()
) {
    override fun toString(): String {
        return "TreasuryDBBank(objectId=$objectId, identifier='$identifier', name=$name, currency=$currency, members=$members)"
    }
}