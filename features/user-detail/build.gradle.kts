plugins {
    id("githubusers.android.feature")
}

dependencies {
    implementation(project(":features:user-detail-api"))
    implementation(project(":libraries:navigation"))
}
