plugins {
    id("githubusers.android.library")
    id("githubusers.android.hilt")
    id("githubusers.android.hilt.test")
    id("githubusers.test")
}

android {
    namespace = "alistar.sample.githubusers.data.remote"

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":data:repository"))
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)

    testImplementation(libs.okhttp.mockWebServer)
}
