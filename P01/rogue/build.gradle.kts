plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.2"
    id("application")
}

group = "org.s21"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.13.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.baulsupp.kolja:jcurses:0.9.5.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest.attributes["Main-Class"] = "org.s21.Main"
}

tasks.shadowJar {
    archiveBaseName.set("Rogue")
    archiveClassifier.set("")
    archiveVersion.set("1.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass.set("org.s21.Main")
}

tasks.register("Rogue", Exec::class) {
    group = "application"
    dependsOn("shadowJar")
    println("Start Rogue Jar file")
    executable = "java"
    args = listOf("-jar", "build/libs/Rogue-1.0.jar")
}