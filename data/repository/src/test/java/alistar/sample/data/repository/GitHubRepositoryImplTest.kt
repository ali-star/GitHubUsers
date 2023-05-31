package alistar.sample.data.repository

import alistar.sample.data.repository.datasource.GitHubDataSource
import alistar.sample.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import alistar.sample.githubusers.libraries.test.exception.TestException
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GitHubRepositoryImplTest : TestCase() {

    private val robot = Robot()

    @Before
    fun setup() {
        robot.setup()
    }

    @Test
    fun test_successful_getUserDetail() {
        RUN_UNIT_TEST(robot) {
            GIVEN { mockSuccessfulGetUserDetail() }
            WHEN { callGetUserDetail() }
            THEN { checkUserDetailSuccessfulResult() }
        }
    }

    @Test
    fun test_failure_getUserDetail() {
        RUN_UNIT_TEST(robot) {
            GIVEN { mockFailureGetUserDetail() }
            WHEN { callGetUserDetail() }
            THEN { checkUserDetailFailureResult() }
        }
    }

    private class Robot : BaseRobot() {

        private val gitHubDataSource: GitHubDataSource = mockk()
        private val gitHubRepository = GitHubRepositoryImpl(gitHubDataSource)
        private lateinit var getUserDetailResultList: List<UserDetail>
        private var testExceptionThrown = false

        override fun setup() {
            super.setup()
            testExceptionThrown = false
        }

        fun mockSuccessfulGetUserDetail() {
            coEvery { gitHubDataSource.getUserDetail(any()) } answers {
                RepoUserDetail(
                    username = "ali-star",
                    name = "Ali Mohseni Rad",
                    photoUrl = "photoUrl",
                    followersCount = 10,
                    followingCount = 10,
                    company = "organization",
                    location = "Amsterdam, Netherlands",
                    twitterUsername = "@ali-star",
                    blog = "alimohsenirad.ir",
                )
            }
        }

        fun mockFailureGetUserDetail() {
            coEvery { gitHubDataSource.getUserDetail(any()) } throws TestException
        }

        fun callGetUserDetail() = runBlocking {
            try {
                getUserDetailResultList = gitHubRepository.getUserDetail("ali-star").toList()
            } catch (ignored: TestException) {
                testExceptionThrown = true
            }
        }

        fun checkUserDetailSuccessfulResult() {
            assertEquals(1, getUserDetailResultList.size)
            assertTrue(!testExceptionThrown)
        }

        fun checkUserDetailFailureResult() {
            assertTrue(testExceptionThrown)
        }
    }
}
