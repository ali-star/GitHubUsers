package alistar.sample.githubusers

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.io.File

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }

        dependencies {
            add("implementation", libs.findLibrary("androidx.compose.runtime").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.tooling").get())
            add("implementation", libs.findLibrary("androidx.compose.material").get())
            add("implementation", libs.findLibrary("androidx.compose.material.iconsExtended").get())
            add("implementation", libs.findLibrary("androidx.activity.compose").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.test").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.junit4").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.testManifest").get())
            add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
            add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
        }
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
