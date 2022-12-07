plugins {
    id("githubusers.android.application")
    id("githubusers.android.application.compose")
    id("githubusers.android.hilt")
    /*id(GradlePlugin.ANDROID_APPLICATION)
    id(GradlePlugin.KOTLIN_ANDROID)
    id(GradlePlugin.KOTLIN_QUALITY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)*/
}

android {
    defaultConfig {
        applicationId = "alistar.sample.githubusers"
        versionCode = 1
        versionName = "1.0.0"
        compileSdk = 31
        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        setProperty("archivesBaseName", "${parent?.name?.capitalize()}-$versionName")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    namespace = "com.alistar.sample.githubusers"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":libraries:design"))
    implementation(project(":libraries:navigation"))
    implementation(project(":features:search"))
    implementation(project(":features:user-detail"))
    implementation(project(":data:repository"))
    implementation(project(":data:remote"))
    /*implementation(Deps.android.ktx)
    implementation(Deps.android.lifecycle)
    implementation(Deps.android.activityCompose)
    implementation(Deps.compose.ui)
    implementation(Deps.compose.material)
    implementation(Deps.compose.uiTooling)
    implementation(Deps.hilt.android)
    implementation(Deps.hilt.hiltNavCompose)

    addComposeDependencies()

    kapt(Deps.hilt.compiler)
    kaptTest(Deps.hilt.compiler)
    kaptAndroidTest(Deps.hilt.compiler)*/
}
