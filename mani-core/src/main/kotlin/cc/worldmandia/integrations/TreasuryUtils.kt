package cc.worldmandia.integrations

import cc.worldmandia.database.DataBaseAPI
import cc.worldmandia.database.objects.TreasuryDBBank
import cc.worldmandia.database.objects.TreasuryDBCurrency
import cc.worldmandia.database.objects.TreasuryDBUser

class TreasuryUtils(
    val currency: MutableSet<TreasuryCurrency>,
    val userDataBase: DataBaseAPI<TreasuryDBUser>,
    val bankDataBase: DataBaseAPI<TreasuryDBBank>
) {
    init {
        userDataBase.createObject(TreasuryDBUser(0, "test", "testUUID", listOf(TreasuryDBCurrency(0, "id", "accId", "100"))))
        println(userDataBase.getObject("name", "test"))
        println(userDataBase.getAllObjects())
    }
}