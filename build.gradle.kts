// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    alias(libs.plugins.kotlin.compose) apply false
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
    }
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.9.0")
        }
    }
}
