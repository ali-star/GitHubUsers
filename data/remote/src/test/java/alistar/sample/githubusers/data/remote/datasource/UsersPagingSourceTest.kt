package alistar.sample.githubusers.data.remote.datasource

import alistar.sample.data.repository.datasource.GitHubDataSource
import alistar.sample.data.repository.model.RepoUser
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import alistar.sample.githubusers.libraries.test.extensions.enqueueResponse
import androidx.paging.PagingSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
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
class UsersPagingSourceTest {

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
        robot = Robot(mockWebServer, gitHubDataSource)
    }

    @Test
    fun test_page1_searchUsers() = RUN_UNIT_TEST(robot) {
        GIVEN { mockSearchUserApiPage1() }
        WHEN { searchUsers(page = null) }
        THEN { checkLoadResultIsAPage() }
        AND { checkPage1Result() }
    }

    @Test
    fun test_page2_searchUsers() = RUN_UNIT_TEST(robot) {
        GIVEN { mockSearchUserApiPage2() }
        WHEN { searchUsers(page = 2) }
        THEN { checkLoadResultIsAPage() }
        AND { checkPage2Result() }
    }

    @Test
    fun test_page3_searchUsers() = RUN_UNIT_TEST(robot) {
        GIVEN { mockSearchUserApiPage3() }
        WHEN { searchUsers(page = 3) }
        THEN { checkLoadResultIsAPage() }
        AND { checkPage3Result() }
    }

    private class Robot(
        private val mockWebServer: MockWebServer,
        private val gitHubDataSource: GitHubDataSource,
    ) : BaseRobot() {

        private var loadResult: PagingSource.LoadResult<Int, RepoUser>? = null

        fun mockSearchUserApiPage1() {
            mockWebServer.enqueueResponse(fileName = "get-search-page1-200.json", code = 200)
        }

        fun mockSearchUserApiPage2() {
            mockWebServer.enqueueResponse(fileName = "get-search-page2-200.json", code = 200)
        }

        fun mockSearchUserApiPage3() {
            mockWebServer.enqueueResponse(fileName = "get-search-page3-200.json", code = 200)
        }

        fun checkLoadResultIsAPage() {
            assertTrue(loadResult is PagingSource.LoadResult.Page)
        }

        fun checkPage1Result() {
            val page = loadResult as PagingSource.LoadResult.Page<Int, RepoUser>
            assertEquals(30, page.data.size)
            assertEquals(null, page.prevKey)
            assertEquals(2, page.nextKey)
            assertEquals("A", page.data[0].username)
        }

        fun checkPage2Result() {
            val page = loadResult as PagingSource.LoadResult.Page<Int, RepoUser>
            assertEquals(30, page.data.size)
            assertEquals(2, page.prevKey)
            assertEquals(3, page.nextKey)
            assertEquals("zonble", page.data[0].username)
        }

        fun checkPage3Result() {
            val page = loadResult as PagingSource.LoadResult.Page<Int, RepoUser>
            assertEquals(30, page.data.size)
            assertEquals(3, page.prevKey)
            assertEquals(4, page.nextKey)
            assertEquals("hathibelagal", page.data[0].username)
        }

        suspend fun searchUsers(page: Int?) {
            loadResult = gitHubDataSource.searchUsers("a").load(
                PagingSource.LoadParams.Refresh(
                    key = page,
                    loadSize = 30,
                    placeholdersEnabled = false
                )
            )
        }
    }
}
