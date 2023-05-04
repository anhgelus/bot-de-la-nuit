plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = rootProject.findProperty("group")!!
version = rootProject.findProperty("version")!!
var jdaVersion = rootProject.findProperty("jda.version")!!

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.dv8tion:JDA:${jdaVersion}")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("${group}.MainKt")
}