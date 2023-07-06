package cc.worldmandia.database.objects

import org.bson.codecs.pojo.annotations.BsonId

class TreasuryDBUser(
    @BsonId
    var objectId: String,
    var name: String?,
    var uuid: String,
    var transactionDBHistoryId: String,
    var currency: List<TreasuryDBCurrency>,
)