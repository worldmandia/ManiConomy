package cc.worldmandia.integrations

import cc.worldmandia.database.DataBaseAPI
import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.database.objects.TreasuryDBUser

class TreasuryUtils(
    val currency: MutableSet<TreasuryCurrency>,
    val userDataBase: DataBaseAPI<TreasuryDBUser>,
    val bankDataBase: DataBaseAPI<TreasuryDBBank>
)