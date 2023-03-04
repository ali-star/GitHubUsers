plugins {
    id("githubusers.android.feature")
}

dependencies {
    implementation(project(":features:search-api"))
    implementation(libs.androidx.paging.compose)
}
