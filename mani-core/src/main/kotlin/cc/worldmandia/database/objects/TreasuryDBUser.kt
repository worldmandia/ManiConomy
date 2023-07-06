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
    @Column(columnDefinition = "VARCHAR(255)")
    var name: String? = null,
    @Column(columnDefinition = "VARCHAR(255)")
    var uuid: String? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    var currency: List<TreasuryDBCurrency> = listOf()
)