plugins {
    id("java")
    id("io.quarkus") version "3.11.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


val quarkusVersion = "3.11.1"
dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusVersion}"))
    implementation("io.quarkus:quarkus-arc") //Implementacion de CDI de quarkus (Motor de comp de negocio)
    implementation("io.quarkus:quarkus-resteasy-reactive") //Motor de rest JAXRS en su forma reactiva
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson") //JSON
    implementation("io.quarkus:quarkus-hibernate-orm-panache") //JPA Hibernate+ repo

    //implementation("org.postgresql:postgresql:42.7.3")
    implementation("io.quarkus:quarkus-jdbc-postgresql:3.11.2")

    //REGISTRO libreria para interactuar con el servidor
    implementation("io.smallrye.reactive:smallrye-mutiny-vertx-consul-client")

   //--HEALT
    implementation("io.quarkus:quarkus-smallrye-health")
}