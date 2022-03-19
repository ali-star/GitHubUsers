import extensions.addHiltTestDependencies
import extensions.addTestDependencies

plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)
}

android {
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }
}

dependencies {
    implementation(projects.data.repository)
    implementation(Deps.coroutines.core)
    implementation(Deps.retrofit.gsonConverter)
    implementation(Deps.retrofit.loggingInterceptor)
    implementation(Deps.paging.runtime)
    implementation(Deps.hilt.android)

    api(Deps.retrofit.retrofit)
    api(Deps.gson)

    kapt(Deps.hilt.compiler)

    addTestDependencies()
    addHiltTestDependencies()

    testImplementation(projects.libraries.test)
    testImplementation(Deps.retrofit.mockWebServer)
}
