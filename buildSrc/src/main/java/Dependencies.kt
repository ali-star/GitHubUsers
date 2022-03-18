object Releases {
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21

    const val compose = "1.1.1"
    const val composeVm = "2.4.1"
    const val activityCompose = "1.4.0"
    const val material = "1.5.0"

    const val coroutines = "1.4.0"

    const val ktx = "1.7.0"

    const val hilt = "2.40.3"
    const val inject = "1"
    const val hiltNavCompose = "1.0.0-beta01"

    const val junit = "4.13.2"
    const val junitExt = "1.1.3"
    const val testRunner = "1.4.0"
    const val robolectric = "4.7.3"
    const val mockk = "1.12.2"
    const val testCore = "1.4.0"

    const val retrofit = "2.9.0"
    const val http3Logging = "4.9.0"
    const val mockWebServer = "4.9.3"

    const val lifecycle = "2.4.1"

    const val gson = "2.8.9"

    const val coil = "1.4.0"

    const val ktlint = "0.43.2"
}

object Deps {
    val android = AndroidDeps
    val compose = ComposeDeps
    val coroutines = CoroutinesDeps
    val hilt = HiltDeps
    val retrofit = RetrofitDeps
    val coil = CoilDeps
    val quality = QualityDeps
    val test = TestDeps
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object AndroidDeps {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
}

object ComposeDeps {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeVm}"
    const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
}

object HiltDeps {
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val test = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val inject = "javax.inject:javax.inject:${Versions.inject}"
    const val hiltNavCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavCompose}"
}

object CoroutinesDeps {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object RetrofitDeps {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.http3Logging}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
}

object CoilDeps {
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val gif = "io.coil-kt:coil-gif:${Versions.coil}"
}

object TestDeps {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"

    const val composeUiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
    const val composeUiJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    const val runner = "androidx.test:runner:${Versions.testRunner}"
    const val core = "androidx.test:core:${Versions.testCore}"
}

object QualityDeps {
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
}
