pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GitHubUsers"
include(":app")
include(":libraries")
include(":libraries:core")
include(":libraries:test")
include(":domain")
include(":data")
include(":data:repository")
include(":data:remote")
include(":libraries:design")
include(":libraries:navigation")
include(":features")
include(":features:search")
include(":features:user-detail")
