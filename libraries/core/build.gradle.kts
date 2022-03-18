plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
}

dependencies {
    implementation(Deps.android.material)
    implementation(Deps.coroutines.core)
}
