package cc.worldmandia.database.objects

import org.bson.codecs.pojo.annotations.BsonId

class TreasuryDBBank(
    @BsonId
    var objectId: String,
    var identifier: String,
    var name: String?,
    var currency: List<TreasuryDBCurrency>
)