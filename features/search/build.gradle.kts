plugins {
    id("githubusers.android.feature")
}

android {
    namespace = "alistar.sample.githubusers.feature.search"
}

dependencies {
    implementation(project(":features:search-api"))
    implementation(libs.androidx.paging.compose)
}
