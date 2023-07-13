plugins {
    kotlin("plugin.lombok") version "1.9.0"
    id("io.freefair.lombok") version "8.1.0"
    kotlin("plugin.serialization") version "1.9.0"
}

dependencies {
    // https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync
    compileOnly("org.mongodb:mongodb-driver-sync:4.10.2")
    // https://mvnrepository.com/artifact/org.litote.kmongo/kmongo-coroutine-serialization
    compileOnly("org.litote.kmongo:kmongo-coroutine-serialization:4.9.0")
    // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    implementation("org.hibernate.orm:hibernate-core:6.2.6.Final")
    // https://mvnrepository.com/artifact/com.h2database/h2
    compileOnly("com.h2database:h2:2.1.214")
    // https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
    compileOnly("org.mariadb.jdbc:mariadb-java-client:3.1.4")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json-jvm
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.1")
}

//kotlinLombok {
//    lombokConfigurationFile(file("lombok.config"))
//}