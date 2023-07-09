package cc.worldmandia.database.objects

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import me.lokka30.treasury.api.economy.account.AccountPermission

@Entity
@AllArgsConstructor
@NoArgsConstructor
class TreasuryDBMember(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var objectId: Long = 0,
        var uuid: String = "",
        @OneToMany(cascade = [CascadeType.ALL])
        var accountPermissions: MutableSet<AccountPermission> = mutableSetOf()
) {

}