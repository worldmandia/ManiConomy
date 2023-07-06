package cc.worldmandia.database.objects

class TreasuryDBCurrency(
    val id: String,
    var balance: String
) {
    override fun toString(): String {
        return "$id:$balance"
    }
}