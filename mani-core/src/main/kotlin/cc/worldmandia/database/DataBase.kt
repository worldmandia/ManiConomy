package cc.worldmandia.database

import cc.worldmandia.database.local.JsonDBAPI
import cc.worldmandia.database.nosql.MongoDBAPI
import cc.worldmandia.database.sql.H2DBAPI
import cc.worldmandia.database.sql.MariaDBAPI


class DataBase<T>(var dbUrlOrPath: String, type: DataBaseType, tClass: Class<T>, dbName: String, dbCollection: String) {
    var dataBaseAPI: DataBaseAPI<T>

    init {
        dataBaseAPI = when (type) {
            DataBaseType.MONGO -> MongoDBAPI(this, tClass, dbName, dbCollection)
            DataBaseType.MARIADB -> MariaDBAPI(this, tClass, dbName, dbCollection)
            DataBaseType.H2 -> H2DBAPI(this, tClass, dbName, dbCollection)
            DataBaseType.JSON -> JsonDBAPI(this, tClass, dbName, dbCollection)
        }
    }
}