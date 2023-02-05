plugins {
    id("githubusers.android.library")
    id("githubusers.android.library.compose")
    id("githubusers.test")
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.kotlinx.coroutines.core)
}
