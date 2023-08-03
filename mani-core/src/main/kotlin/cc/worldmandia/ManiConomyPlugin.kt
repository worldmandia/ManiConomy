package cc.worldmandia

import cc.worldmandia.integrations.TreasuryProvider

fun interface ManiConomyPlugin {
    fun getTreasuryProvider(): TreasuryProvider

}