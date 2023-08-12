plugins {
    id("githubusers.android.feature-api")
}

android {
    namespace = "alistar.sample.githubusers.feature.searchapi"
}

dependencies {
    implementation(libs.androidx.paging.compose)
}
