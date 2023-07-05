package cc.worldmandia.integrations.accessor

import me.lokka30.treasury.api.economy.account.accessor.AccountAccessor
import me.lokka30.treasury.api.economy.account.accessor.NonPlayerAccountAccessor
import me.lokka30.treasury.api.economy.account.accessor.PlayerAccountAccessor

class TreasuryAccessor : AccountAccessor {
    override fun player(): PlayerAccountAccessor {
        return TreasuryPlayerAccountAccessor()
    }

    override fun nonPlayer(): NonPlayerAccountAccessor {
        return TreasuryNonPlayerAccountAccessor()
    }
}