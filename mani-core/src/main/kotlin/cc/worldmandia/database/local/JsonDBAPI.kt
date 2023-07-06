package cc.worldmandia.database.local

import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseAPI

class JsonDBAPI<T>(
    dataBase: DataBase<T>, tClass: Class<T>, dbName: String, dbCollection: String
) : DataBaseAPI<T> {

    init {

    }

    override fun getObject(fieldId: String, fieldValue: Any): T? {
        TODO("Not yet implemented")
    }

    override fun removeObject(fieldId: String, fieldValue: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllObjects(): Set<T> {
        TODO("Not yet implemented")
    }

    override fun contains(fieldId: String, fieldValue: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun replaceObject(fieldId: String, fieldValue: Any, updateData: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun createObject(newObject: T): Boolean {
        TODO("Not yet implemented")
    }
}