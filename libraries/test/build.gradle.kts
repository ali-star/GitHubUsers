plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
}

dependencies {
    implementation(projects.libraries.core)
    implementation(Deps.hilt.android)
    implementation(Deps.android.material)
    implementation(Deps.test.core)
    implementation(Deps.test.runner)
    implementation(Deps.retrofit.mockWebServer)
    implementation(Deps.test.composeUiTest)
    implementation(Deps.test.composeUiTestManifest)
    implementation(Deps.test.composeUiJunit4)
}
