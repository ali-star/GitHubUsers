import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("githubusers.android.library")
    id("githubusers.android.hilt")
    /*id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)*/
}

android {
    /*
     * Please create a file named githubauth.properties inside the root project
     * and add your username (username) and github personal access token (token) in it.
     */
    val authProperties = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "githubauth.properties")))
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "USERNAME", authProperties.getProperty("username"))
            buildConfigField("String", "TOKEN", authProperties.getProperty("token"))
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "USERNAME", authProperties.getProperty("username"))
            buildConfigField("String", "TOKEN", authProperties.getProperty("token"))
        }
    }
}

dependencies {
    implementation(project(":data:repository"))
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)
    /*implementation(projects.data.repository)
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
    testImplementation(Deps.retrofit.mockWebServer)*/
}
