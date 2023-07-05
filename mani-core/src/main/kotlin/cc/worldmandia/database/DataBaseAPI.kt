package cc.worldmandia.database
interface DataBaseAPI<T> {
    fun getObject(fieldId: String, fieldValue: Any): T?
    fun createObject(newObject: T): Boolean
    fun replaceObject(fieldId: String, fieldValue: Any, updateData: T): Boolean
    fun contains(fieldId: String, fieldValue: Any): Boolean
}