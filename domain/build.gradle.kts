plugins {
    id("githubusers.kotlin.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
    implementation(libs.javax.inject)
}
