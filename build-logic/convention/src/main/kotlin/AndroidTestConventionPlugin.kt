import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                testOptions.unitTests.isIncludeAndroidResources = true
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":libraries:test"))
                add("androidTestImplementation", libs.findLibrary("junit4").get())
                add("androidTestImplementation", libs.findLibrary("junit.ext").get())
                add("androidTestImplementation", libs.findLibrary("mockk.android").get())
                add("androidTestImplementation", libs.findLibrary("androidx.test.runner").get())
                add("androidTestImplementation", libs.findLibrary("androidx.test.core").get())
            }
        }
    }
}