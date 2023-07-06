package cc.worldmandia.database.sql

import cc.worldmandia.database.DataBase
import cc.worldmandia.database.DataBaseAPI
import cc.worldmandia.database.objects.TreasuryDBCurrency
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Environment


class SQLDBAPI<T>(dataBase: DataBase<T>, private val tClass: Class<T>, dbName: String, dbCollection: String) :
    DataBaseAPI<T> {
    private val sessionFactory: SessionFactory
    private var session: Session

    init {
        val hibernateProperties = mutableMapOf<String, Any>(
            Environment.SHOW_SQL to "false",
            )
        if (dataBase.dbUrlOrPath.startsWith("jdbc:h2:file:")) {
            hibernateProperties.putAll(
                mapOf(
                    Environment.DIALECT to "org.hibernate.dialect.H2Dialect",
                    Environment.DRIVER to "org.h2.Driver",
                    Environment.URL to "jdbc:h2:file:./${dataBase.pluginDirectory}/$dbName;DB_CLOSE_DELAY=-1",
                    Environment.HBM2DDL_AUTO to "update"
                )
            )
        } else if (dataBase.dbUrlOrPath.startsWith("jdbc:mariadb:")) {
            hibernateProperties.putAll(
                mapOf(
                    Environment.DIALECT to "org.hibernate.dialect.MariaDBDialect",
                    Environment.DRIVER to "org.mariadb.jdbc.Driver",
                    Environment.URL to "jdbc:mariadb://${dataBase.dbUrlOrPath}/${dbName}",
                    Environment.USER to dataBase.user,
                    Environment.PASS to dataBase.pass
                )
            )
        }


        val serviceRegistry = StandardServiceRegistryBuilder()
            .applySettings(hibernateProperties)
            .build()

        val metadata = MetadataSources(serviceRegistry)
            .addAnnotatedClass(TreasuryDBCurrency::class.java)
            //.addAnnotatedClass(TreasuryDBTransactionHistory::class.java)
            .addAnnotatedClass(tClass)
            .buildMetadata()

        sessionFactory = metadata.buildSessionFactory()
        session = sessionFactory.openSession()
    }

    override fun getObject(fieldId: String, fieldValue: Any): T? {
        if (!session.isConnected) {
            session = sessionFactory.openSession()
        }
        val query = session.createQuery("FROM ${tClass.simpleName} WHERE $fieldId = :value", tClass)
        query.setParameter("value", fieldValue)
        return query.uniqueResult()
    }

    override fun removeObject(fieldId: String, fieldValue: Any): Boolean {
        if (!session.isConnected) {
            session = sessionFactory.openSession()
        }
        val query = session.createQuery("FROM ${tClass.simpleName} WHERE $fieldId = :value", tClass)
        query.setParameter("value", fieldValue)
        val result = query.uniqueResult()
        if (result != null) {
            session.remove(result)
            return true
        }
        return false
    }

    override fun getAllObjects(): Set<T> {
        if (!session.isConnected) {
            session = sessionFactory.openSession()
        }
        return session.createQuery("from ${tClass.simpleName}", tClass).list().toSet()
    }

    override fun contains(fieldId: String, fieldValue: Any): Boolean {
        if (!session.isConnected) {
            session = sessionFactory.openSession()
        }
        val query = session.createQuery("FROM ${tClass.simpleName} WHERE $fieldId = :value", tClass)
        return query.setParameter("value", fieldValue).uniqueResult() != null
    }

    override fun replaceObject(fieldId: String, fieldValue: Any, updateData: T): Boolean {
        if (!session.isConnected) {
            session = sessionFactory.openSession()
        }
        session.merge(updateData)
        return true
    }

    override fun createObject(newObject: T): Boolean {
        if (!session.isConnected) {
            session = sessionFactory.openSession()
        }
        return try {
            session.save(newObject)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}