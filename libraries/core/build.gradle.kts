plugins {
    id("githubusers.android.library")
    id("githubusers.android.library.compose")
    id("githubusers.test")
}

android {
    namespace = "alistar.sample.githubusers.libraries.core"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.kotlinx.coroutines.core)
}
