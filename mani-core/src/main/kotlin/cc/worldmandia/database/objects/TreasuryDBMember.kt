package cc.worldmandia.database.objects

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@AllArgsConstructor
@NoArgsConstructor
class TreasuryDBMember(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var objectId: Long = 0,
        var uuid: String = "",
        @OneToMany(cascade = [CascadeType.ALL])
        var accountPermissions: MutableSet<TreasuryAccountPermission> = mutableSetOf()
) {
    override fun toString(): String {
        return "TreasuryDBMember(objectId=$objectId, uuid='$uuid', accountPermissions=$accountPermissions)"
    }
}