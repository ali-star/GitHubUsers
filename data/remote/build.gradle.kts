import extensions.addHiltTestDependencies
import extensions.addTestDependencies
import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)
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
