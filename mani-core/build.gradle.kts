plugins {
    kotlin("plugin.lombok") version "1.8.22"
    id("io.freefair.lombok") version "5.3.0"
}

dependencies {
    // https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync
    implementation("org.mongodb:mongodb-driver-sync:4.10.1")
    // https://mvnrepository.com/artifact/org.litote.kmongo/kmongo-coroutine-serialization
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:4.9.0")
    // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    implementation("org.hibernate.orm:hibernate-core:6.2.6.Final")
    // https://mvnrepository.com/artifact/com.h2database/h2
    implementation("com.h2database:h2:2.1.214")
    // https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.4")
}

kotlinLombok {
    lombokConfigurationFile(file("lombok.config"))
}