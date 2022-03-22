package alistar.sample.githubusers.data.remote.datasource

import alistar.sample.data.repository.datasource.GitHubDataSource
import alistar.sample.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UI_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import alistar.sample.githubusers.libraries.test.extensions.enqueueResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class GitHubDataSourceTest : TestCase() {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gitHubDataSource: GitHubDataSource

    @Inject
    lateinit var mockWebServer: MockWebServer

    private lateinit var robot: Robot

    @Before
    fun setup() {
        hiltRule.inject()
        robot = Robot(gitHubDataSource, mockWebServer)
    }

    @Test
    fun test_getUserDetail() {
        RUN_UI_TEST(robot) {
            GIVEN { mockUserDetailApi() }
            WHEN { callGetUserDetail() }
            THEN { checkGetUserDetailResult() }
        }
    }

    private class Robot(
        private val gitHubDataSource: GitHubDataSource,
        private val mockWebServer: MockWebServer
    ) : BaseRobot() {

        private lateinit var user: RepoUserDetail

        fun mockUserDetailApi() {
            mockWebServer.enqueueResponse(fileName = "get-user-200.json", 200)
        }

        fun callGetUserDetail() = runBlocking {
            user = gitHubDataSource.getUserDetail("ali-star")
        }

        fun checkGetUserDetailResult() {
            assertEquals("ali-star", user.username)
            assertEquals("Ali Mohseni Rad", user.name)
            assertEquals("https://avatars.githubusercontent.com/u/5895322?v=4", user.photoUrl)
            assertEquals(6, user.followersCount)
            assertEquals(6, user.followingCount)
            assertEquals("My Company", user.company)
            assertEquals("Amsterdam, Netherlands", user.location)
            assertEquals("@ali-star", user.twitterUsername)
            assertEquals("alimohsenirad.ir", user.blog)
        }
    }
}
