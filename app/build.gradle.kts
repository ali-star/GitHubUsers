plugins {
    // id("githubusers.android.application")
    // id("githubusers.android.hilt")
    /*id(GradlePlugin.ANDROID_APPLICATION)
    id(GradlePlugin.KOTLIN_ANDROID)
    id(GradlePlugin.KOTLIN_QUALITY)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.HILT)*/
}

/*android {
    defaultConfig {
        applicationId = "alistar.sample.githubusers"
        versionCode = Releases.versionCode
        versionName = Releases.versionName
        compileSdk = Versions.compileSdk
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

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

    addComposeConfig()

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.libraries.design)
    implementation(projects.libraries.navigation)
    implementation(projects.features.search)
    implementation(projects.features.userDetail)
    implementation(projects.data.repository)
    implementation(projects.data.remote)
    implementation(Deps.android.ktx)
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
    kaptAndroidTest(Deps.hilt.compiler)
}*/
