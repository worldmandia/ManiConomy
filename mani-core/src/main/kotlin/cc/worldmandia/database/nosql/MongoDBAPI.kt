package cc.worldmandia.database.nosql

import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseAPI
import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.bson.Document
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider


class MongoDBAPI<T>(dataBase: DataBase<T>, tClass: Class<T>, dbName: String, dbCollection: String) : DataBaseAPI<T> {
    private val mongoClient: MongoClient
    private val collection: MongoCollection<T>

    init {
        val pojoCodecProvider: CodecProvider = PojoCodecProvider.builder().automatic(true).build()
        val pojoCodecRegistry: CodecRegistry =
            fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider))
        mongoClient = MongoClients.create(dataBase.dbUrlOrPath)
        collection = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry)
            .getCollection(dbCollection, tClass)
    }

    override fun getObject(fieldId: String, fieldValue: Any): T? {
        return collection.find(Filters.eq(fieldId, fieldValue)).first()
    }

    override fun getAllObjects(): Set<T> {
        return collection.find().toHashSet()
    }

    override fun removeObject(fieldId: String, fieldValue: Any): Boolean {
       return collection.deleteOne(Filters.eq(fieldId, fieldValue)).wasAcknowledged()
    }

    override fun createObject(newObject: T): Boolean {
        return collection.insertOne(newObject).wasAcknowledged()
    }

    override fun replaceObject(fieldId: String, fieldValue: Any, updateData: T): Boolean {
        return collection.replaceOne(Document(fieldId, fieldValue), updateData).wasAcknowledged()
    }

    override fun contains(fieldId: String, fieldValue: Any): Boolean {
        return collection.countDocuments(Filters.eq(fieldId, fieldValue)) > 0
    }
}