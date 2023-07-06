package cc.worldmandia.database.objects

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.bson.codecs.pojo.annotations.BsonId

@Entity
@NoArgsConstructor
@AllArgsConstructor
class TreasuryDBUser(
    @BsonId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: String = "",
    var name: String? = null,
    var uuid: String = "",
    @OneToMany(cascade = [CascadeType.ALL])
    var currency: List<TreasuryDBCurrency> = listOf()
)