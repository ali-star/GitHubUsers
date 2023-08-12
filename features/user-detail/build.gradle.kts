plugins {
    id("githubusers.android.feature")
}

android {
    namespace = "alistar.sample.githubusers.feature.userdetail"
}

dependencies {
    implementation(project(":features:user-detail-api"))
    implementation(project(":libraries:navigation"))
}
