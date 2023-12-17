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
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher

class GitHubRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()
    private val robot = Robot(testDispatcher)

    @Before
    fun setup() {
        robot.setup()
    }

    @Test
    fun test_successful_getUserDetail() = RUN_UNIT_TEST(robot, testDispatcher) {
        GIVEN { mockSuccessfulGetUserDetail() }
        WHEN { callGetUserDetail() }
        THEN { checkUserDetailSuccessfulResult() }
    }

    @Test
    fun test_failure_getUserDetail() = RUN_UNIT_TEST(robot, testDispatcher) {
        GIVEN { mockFailureGetUserDetail() }
        WHEN { callGetUserDetail() }
        THEN { checkUserDetailFailureResult() }
    }
}

private class Robot(testDispatcher: TestDispatcher) : BaseRobot() {

    private val gitHubDataSource: GitHubDataSource = mockk()
    private val gitHubRepository = GitHubRepositoryImpl(gitHubDataSource, testDispatcher)
    private var getUserDetailResult: UserDetail? = null
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

    suspend fun callGetUserDetail() {
        try {
            getUserDetailResult = gitHubRepository.getUserDetail("ali-star")
        } catch (ignored: TestException) {
            testExceptionThrown = true
        }
    }

    fun checkUserDetailSuccessfulResult() {
        assertTrue(getUserDetailResult != null)
        assertTrue(!testExceptionThrown)
    }

    fun checkUserDetailFailureResult() {
        assertTrue(testExceptionThrown)
    }
}

