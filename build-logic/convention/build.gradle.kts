plugins {
    `kotlin-dsl`
}

group = "alistar.sample.githubusers.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "githubusers.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "githubusers.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "githubusers.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "githubusers.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "githubusers.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "githubusers.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidFeatureApi") {
            id = "githubusers.android.feature-api"
            implementationClass = "AndroidFeatureApiConventionPlugin"
        }
        register("test") {
            id = "githubusers.test"
            implementationClass = "TestConventionPlugin"
        }
        register("androidTest") {
            id = "githubusers.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("androidHilt") {
            id = "githubusers.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidHiltTest") {
            id = "githubusers.android.hilt.test"
            implementationClass = "AndroidHiltTestConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "githubusers.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}
