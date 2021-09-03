plugins {
    `kotlin-dsl`
    groovy
}

repositories {
    mavenCentral()
}

dependencies {
    compile(localGroovy())
}