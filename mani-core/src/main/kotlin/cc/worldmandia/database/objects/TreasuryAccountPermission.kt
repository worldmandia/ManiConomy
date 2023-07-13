package cc.worldmandia.database.objects

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import me.lokka30.treasury.api.common.misc.TriState
import me.lokka30.treasury.api.economy.account.AccountPermission

@AllArgsConstructor
@NoArgsConstructor
@Entity
class TreasuryAccountPermission(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var objectId: Long = 0,
    var bankId: String = "",
    var accountPermission: MutableSet<AccountPermission> = mutableSetOf(),
    var state: MutableSet<TriState> = mutableSetOf()
) {

}