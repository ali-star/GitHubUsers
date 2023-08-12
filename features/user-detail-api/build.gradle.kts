plugins {
    id("githubusers.android.feature")
}

android {
    namespace = "alistar.sample.githubusers.feature.userdetailapi"
}

dependencies {
    implementation(project(":domain"))
}
