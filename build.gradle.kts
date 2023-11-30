apply plugin: 'java-library'
apply plugin: 'eclipse'

// Para agregar un comando que se llama aggregate, que es de Serenity y es para construir el reporte
apply plugin: 'net.serenity-bdd.aggregator'


repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

// El bloque buildscript es para configurar a Gradle como tal
// Es para descargar las dependencias que configuraran a Gradle como tal, no hacen parte del codigo fuente.
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
    dependencies {

        classpath("net.serenity-bdd:serenity-gradle-plugin:2.0.80")
    }
}

dependencies {
    // Dependencias para poder automatizar con Serenity
    //compile 'net.serenity-bdd:serenity-junit:2.0.80'
    implementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation 'junit:junit:4.12'
    implementation 'net.serenity-bdd:serenity-cucumber:1.9.45'
    implementation 'net.serenity-bdd:serenity-core:2.0.80'
    implementation 'org.slf4j:slf4j-simple:1.7.7'
    implementation 'com.google.guava:guava:23.0'
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.17'
}


test {
//Ignorar los fallos es ejecutar todas las pruebas asi alguna falle
    ignoreFailures = true
}
// Configurar el encoding del proyecto
tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}
test.finalizedBy(aggregate)
// Continue asi falle alguna tarea de Gradle
gradle.startParameter.continueOnFailure = true