package cc.worldmandia.database.sql

import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseAPI
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Environment


class H2DBAPI<T>(dataBase: DataBase<T>, private val tClass: Class<T>, dbName: String, dbCollection: String) :
    DataBaseAPI<T> {
    private val sessionFactory: SessionFactory
    private val session: Session

    init {
        val hibernateProperties = mapOf(
            Environment.DIALECT to "org.hibernate.dialect.H2Dialect",
            Environment.DRIVER to "org.h2.Driver",
            Environment.URL to "jdbc:h2:${dataBase.dbUrlOrPath}/$dbName;DB_CLOSE_DELAY=-1;MVCC=true",
            Environment.SHOW_SQL to "true",
            Environment.HBM2DDL_AUTO to "create-drop"
        )

        val serviceRegistry = StandardServiceRegistryBuilder()
            .applySettings(hibernateProperties)
            .build()

        val metadata = MetadataSources(serviceRegistry)
            .addAnnotatedClass(tClass)
            .buildMetadata()

        sessionFactory = metadata.buildSessionFactory()
        session = sessionFactory.openSession()
    }

    override fun getObject(fieldId: String, fieldValue: Any): T? {
        return session.createQuery("FROM ${tClass.simpleName} WHERE $fieldId = :fieldValue", tClass).uniqueResult()
    }

    override fun removeObject(fieldId: String, fieldValue: Any): Boolean {
        val result =
            session.createQuery("FROM ${tClass.simpleName} WHERE $fieldId = :fieldValue", tClass).uniqueResult()
        if (result != null) {
            session.remove(result)
            return true
        }
        return false
    }

    override fun getAllObjects(): Set<T> {
        return session.createQuery("from ${tClass.simpleName}", tClass).list().toSet()
    }

    override fun contains(fieldId: String, fieldValue: Any): Boolean {
        return session.createQuery("FROM ${tClass.simpleName} WHERE $fieldId = :fieldValue", tClass)
            .uniqueResult() != null
    }

    override fun replaceObject(fieldId: String, fieldValue: Any, updateData: T): Boolean {
        session.merge(updateData)
        return true
    }

    override fun createObject(newObject: T): Boolean {
        session.persist(newObject)
        return true
    }

}