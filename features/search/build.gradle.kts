import extensions.addHiltTestDependencies
import extensions.addTestDependencies

plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.design)
    implementation(projects.libraries.core)
    implementation(Deps.android.lifecycle)
    implementation(Deps.android.activityCompose)
    implementation(Deps.compose.viewModel)
    implementation(Deps.coroutines.core)
    implementation(Deps.coil.coil)
    implementation(Deps.hilt.android)
    implementation(Deps.paging.compose)

    kapt(Deps.hilt.compiler)

    addTestDependencies()
    addHiltTestDependencies()

    testImplementation(projects.libraries.test)
}
