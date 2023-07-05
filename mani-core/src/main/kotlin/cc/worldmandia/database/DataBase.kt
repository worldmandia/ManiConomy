package cc.worldmandia.database

import cc.worldmandia.database.nosql.MongoDBAPI


class DataBase<T>(var dbUrlOrPath: String, type: DataBaseType, tClass: Class<T>, dbName: String, dbCollection: String) {
    private var dataBaseAPI: DataBaseAPI<T>? = null

    init {
        when (type) {
            //DataBaseType.LOCAL -> dataBaseAPI = JsonDBAPI(this, tClass)
            DataBaseType.MONGO -> dataBaseAPI = MongoDBAPI(this, tClass, dbName, dbCollection)
            else -> {}
        }
    }
}