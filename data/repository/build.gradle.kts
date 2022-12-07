plugins {
    id("githubusers.android.library")
    id("githubusers.android.hilt")
    /*id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)*/
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":libraries:core"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)
    /*implementation(Deps.paging.common)
    implementation(Deps.coroutines.core)
    implementation(Deps.hilt.android)*/
}

/*import extensions.addHiltTestDependencies
import extensions.addTestDependencies

plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.core)
    implementation(Deps.paging.common)
    implementation(Deps.coroutines.core)
    implementation(Deps.hilt.android)

    kapt(Deps.hilt.compiler)

    testImplementation(projects.libraries.test)

    addTestDependencies()
    addHiltTestDependencies()
}*/
