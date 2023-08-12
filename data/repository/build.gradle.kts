plugins {
    id("githubusers.android.library")
    id("githubusers.android.hilt")
    id("githubusers.test")
}

android {
    namespace = "alistar.sample.githubusers.data.repository"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":libraries:core"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)
}
