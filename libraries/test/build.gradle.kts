plugins {
    id("githubusers.android.library")
    id("githubusers.android.library.compose")
    id("githubusers.android.hilt")
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.okhttp.mockWebServer)
    implementation(libs.androidx.compose.ui.junit4)
    implementation(libs.androidx.compose.ui.test)
}
