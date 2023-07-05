package cc.worldmandia.database

import cc.worldmandia.database.nosql.MongoDBAPI
import cc.worldmandia.database.sql.MariaDBAPI


class DataBase<T>(var dbUrlOrPath: String, type: DataBaseType, tClass: Class<T>, dbName: String, dbCollection: String) {
    var dataBaseAPI: DataBaseAPI<T>

    init {
        dataBaseAPI = when (type) {
            DataBaseType.MONGO -> MongoDBAPI(this, tClass, dbName, dbCollection)
            DataBaseType.MARIADB -> MariaDBAPI(this, tClass, dbName, dbCollection)
            else -> MongoDBAPI(this, tClass, dbName, dbCollection)
        }
    }
}