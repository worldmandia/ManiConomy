package cc.worldmandia.database

import cc.worldmandia.database.local.JsonDBAPI
import cc.worldmandia.database.nosql.MongoDBAPI
import cc.worldmandia.database.sql.SQLDBAPI


class DataBase<T>(var dbUrlOrPath: String, type: DataBaseType, tClass: Class<T>, dbName: String, dbCollection: String) {
    var dataBaseAPI: DataBaseAPI<T>

    init {
        dataBaseAPI = when (type) {
            DataBaseType.MONGO -> MongoDBAPI(this, tClass, dbName, dbCollection)
            DataBaseType.SQL -> SQLDBAPI(this, tClass, dbName, dbCollection)
            DataBaseType.JSON -> JsonDBAPI(this, tClass, dbName, dbCollection)
        }
    }
}