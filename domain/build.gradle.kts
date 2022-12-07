plugins {
    id("githubusers.kotlin.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common)
    implementation(libs.javax.inject)
}

/*plugins {
    id(GradlePlugin.KOTLIN_LIBRARY)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Deps.coroutines.core)
    implementation(Deps.paging.common)
    implementation(Deps.hilt.inject)
}*/
