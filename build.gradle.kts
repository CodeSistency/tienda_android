buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }
}

plugins {

    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}