package gradle

import extensions.addDefaultConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("gradle.quality")
    id("kotlin-parcelize")
}

android {
    addDefaultConfig()

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
            consumerProguardFiles("proguard-rules.pro")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
