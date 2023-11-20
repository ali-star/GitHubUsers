package alistar.sample.githubusers

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.Test

class ArchitectureTest {

    // domain (application core)
    private val domain = Layer(
        name = "Domain",
        definedBy = "alistar.sample.githubusers.domain..",
    )

    // feature api (presentation ports)
    private val searchApi = Layer(
            name = "SearchApi",
            definedBy = "alistar.sample.githubusers.presentation.api.search..",
        )
    private val userDetailApi = Layer(
            name = "UserDetailApi",
            definedBy = "alistar.sample.githubusers.presentation.api.userdetail..",
        )

    // feature apis (presentation adapters)
    private val search = Layer(
        name = "Search",
        definedBy = "alistar.sample.githubusers.presentation.feature.search..",
    )
    private val userDetail =
        Layer(
            name = "UserDetail",
            definedBy = "alistar.sample.githubusers.presentation.feature.userdetail..",
        )

    // data (data ports)
    private val repository = Layer(
        name = "Repository",
        definedBy = "alistar.sample.githubusers.data.repository..",
    )

    // data (data adapters)
    private val remote = Layer(
        name = "Remote",
        definedBy = "alistar.sample.githubusers.data.remote..",
    )

    @Test
    fun `hexagonal architecture layers have correct dependencies`() {
        val scope = Konsist.scopeFromProject()
        scope.assertArchitecture {
            domain.dependsOn(searchApi) // Should fail

            searchApi.dependsOn(domain)
            userDetailApi.dependsOn(domain)

            search.dependsOn(searchApi)
            userDetail.dependsOn(userDetailApi)

            repository.dependsOn(domain)

            remote.dependsOn(repository)
        }
    }
}
