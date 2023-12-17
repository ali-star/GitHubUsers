import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                testOptions.unitTests.isIncludeAndroidResources = true
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":libraries:test"))
                add("testImplementation", libs.findLibrary("junit4").get())
                add("testImplementation", libs.findLibrary("junit.ext").get())
                add("testImplementation", libs.findLibrary("mockk.unit").get())
                add("testImplementation", libs.findLibrary("robolectric.unit").get())
                add("testImplementation", libs.findLibrary("androidx.test.runner").get())
                add("testImplementation", libs.findLibrary("androidx.test.core").get())
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
            }
        }
    }
}