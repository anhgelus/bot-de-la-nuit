import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.8.20"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    application
}

group = rootProject.findProperty("group")!!
version = rootProject.findProperty("version")!!
val jdaVersion = rootProject.findProperty("jda.version")!!
val tomlVersion = rootProject.findProperty("4koma.version")!!

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.dv8tion:JDA:${jdaVersion}")
    implementation("cc.ekblad:4koma:${tomlVersion}")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("${group}.MainKt")
    // required by shadow jar...
    mainClassName = "${group}.MainKt"
}

tasks.withType<ShadowJar> {
    archiveFileName.set("bot-de-la-nuit.jar")
}
