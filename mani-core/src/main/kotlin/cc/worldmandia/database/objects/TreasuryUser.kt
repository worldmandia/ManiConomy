package cc.worldmandia.database.objects

import org.bson.codecs.pojo.annotations.BsonId

class TreasuryUser(
    @BsonId
    var objectId: String,
    var name: String,
    var uuid: String,
    var transactionDBHistoryId: String,
    var currency: HashMap<String, String>,
)