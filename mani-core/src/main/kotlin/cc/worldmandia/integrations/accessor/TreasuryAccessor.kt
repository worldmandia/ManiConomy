package cc.worldmandia.integrations.accessor

import cc.worldmandia.integrations.TreasuryUtils
import me.lokka30.treasury.api.economy.account.accessor.AccountAccessor
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import me.lokka30.treasury.api.economy.account.accessor.PlayerAccountAccessor

class TreasuryAccessor(
    private val utils: TreasuryUtils
) : AccountAccessor {
    override fun player(): PlayerAccountAccessor {
        return TreasuryPlayerAccountAccessor(utils)
    }

    override fun nonPlayer(): NonPlayerAccountAccessor {
        return TreasuryNonPlayerAccountAccessor(utils)
    }
}