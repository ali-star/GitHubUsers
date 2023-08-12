import java.util.Locale

plugins {
    id("githubusers.android.application")
    id("githubusers.android.application.compose")
    id("githubusers.android.hilt")
}

android {
    defaultConfig {
        applicationId = "alistar.sample.githubusers"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        setProperty("archivesBaseName", "${parent?.name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }}-$versionName")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    namespace = "alistar.sample.githubusers"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":libraries:design"))
    implementation(project(":libraries:navigation"))
    implementation(project(":features:search-api"))
    implementation(project(":features:search"))
    implementation(project(":features:user-detail-api"))
    implementation(project(":features:user-detail"))
    implementation(project(":data:repository"))
    implementation(project(":data:remote"))
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
}
