plugins {
    id("githubusers.android.library")
    id("githubusers.android.library.compose")
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.kotlinx.coroutines.core)
}

/*
import extensions.addTestDependencies

plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
}

dependencies {
    implementation(Deps.android.material)
    implementation(Deps.coroutines.core)

    testImplementation(projects.libraries.test)
    addTestDependencies()
}
*/
