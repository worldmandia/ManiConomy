package cc.worldmandia.database.sql

import cc.worldmandia.database.DataBaseAPI

class MariaDBAPI<T>(): DataBaseAPI<T> {
    override fun getObject(fieldId: String, fieldValue: Any): T? {
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