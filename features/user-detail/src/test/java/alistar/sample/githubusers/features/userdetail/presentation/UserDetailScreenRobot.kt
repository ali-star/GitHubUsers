package alistar.sample.githubusers.features.userdetail.presentation

import alistar.sample.githubusers.features.userdetailapi.item.UserDetailItem
import alistar.sample.githubusers.features.userdetailapi.usecase.GetUserDetailUseCase
import alistar.sample.githubusers.libraries.test.BaseRobot
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import alistar.sample.githubusers.libraries.core.result.Result
import alistar.sample.githubusers.libraries.navigation.DestinationArgs
import androidx.compose.material.Surface
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.lifecycle.SavedStateHandle
import io.mockk.verify

class UserDetailScreenRobot(
    private val composeContentTestRole: ComposeContentTestRule
) : BaseRobot() {

    private val getUserDetailUseCase: GetUserDetailUseCase = mockk()
    private lateinit var viewModel: UserDetailScreenViewModel
    private val openLink: (link: String) -> Unit = mockk()
    private val navigateBack: () -> Unit = mockk {
        every { this@mockk() } returns Unit
    }

    fun mockGetUserDetailUseCaseWithSuccessResult() {
        every { getUserDetailUseCase(username = any()) } returns flow {
            val userDetailItem = UserDetailItem(
                username = "ali-star",
                name = "Ali",
                photoUrl = "photoUrl",
                followersCount = 6,
                followingCount = 9,
                organizations = "an organization",
                location = "Netherlands",
                twitterUsername = "ali-star-twitter",
                blogUrl = "https://blog.com",
            )
            emit(Result.Success(userDetailItem))
        }
    }

    fun mockGetUserDetailUseCaseWithFailureResult() {
        every { getUserDetailUseCase(username = any()) } returns flow {
            emit(Result.Error(Exception("test exception")))
        }
    }

    fun mockGetUserDetailUseCaseWithLoadingResult() {
        every { getUserDetailUseCase(username = any()) } returns flow {
            emit(Result.Loading)
        }
    }

    fun createViewModel() {
        val savedStateHandle: SavedStateHandle = mockk(relaxed = true) {
            every { this@mockk.get<String>(DestinationArgs.USERNAME) } returns "ali-star"
        }
        viewModel = UserDetailScreenViewModel(
            savedStateHandle = savedStateHandle,
            getUserDetailUseCase = getUserDetailUseCase
        )
    }

    fun createUserDetailScreen() {
        composeContentTestRole.setContent {
            Surface {
                UserDetailScreen(
                    userDetailScreenViewModel = viewModel,
                    openLink = openLink,
                    navigateBack = navigateBack
                )
            }
        }
    }

    fun checkLoadingStateIsDisplayed() {
        composeContentTestRole.onNodeWithTag("loadingState").assertIsDisplayed()
    }

    fun checkLoadingStateIsNotDisplayed() {
        composeContentTestRole.onNodeWithTag("loadingState").assertDoesNotExist()
    }

    fun checkErrorStateIsDisplayed() {
        composeContentTestRole.onNodeWithTag("errorState").assertIsDisplayed()
    }

    fun checkErrorStateIsNotDisplayed() {
        composeContentTestRole.onNodeWithTag("errorState").assertDoesNotExist()
    }

    fun checkResultStateIsDisplayed() {
        composeContentTestRole.onNodeWithContentDescription("avatarImage").assertIsDisplayed()
        composeContentTestRole.onNodeWithText("Ali").assertIsDisplayed()
        composeContentTestRole.onNodeWithText("ali-star").assertIsDisplayed()
        composeContentTestRole.onNodeWithText("6 followers • 9 following").assertIsDisplayed()
        composeContentTestRole.onNodeWithContentDescription("organizations").assertExists()
        composeContentTestRole.onNodeWithText("an organization").assertExists()
        composeContentTestRole.onNodeWithContentDescription("location").assertExists()
        composeContentTestRole.onNodeWithText("Netherlands").assertExists()
        composeContentTestRole.onNodeWithContentDescription("twitterUsername").assertExists()
        composeContentTestRole.onNodeWithText("ali-star-twitter").assertExists()
        composeContentTestRole.onNodeWithText("https://blog.com").assertExists()
    }

    fun checkResultStateIsNotDisplayed() {
        composeContentTestRole.onNodeWithTag("resultState").assertDoesNotExist()
    }

    fun checkTopBarIsDisplayed() {
        composeContentTestRole.onNodeWithTag("topBar").assertIsDisplayed()
    }

    fun clickOnBackButton() {
        composeContentTestRole.onNodeWithContentDescription("backButton").performClick()
    }

    fun checkBackButtonIsClicked() {
        verify { navigateBack() }
    }

    fun mockOpenBlogLinkFunction() {
        every { openLink("https://blog.com") } returns Unit
    }

    fun clickOnBlogLink() {
        composeContentTestRole.onNodeWithText("https://blog.com")
            .performSemanticsAction(SemanticsActions.OnClick)
    }

    fun checkOpenBlogLinkGotCalled() {
        verify { openLink("https://blog.com") }
    }

    fun mockOpenTwitterLinkFunction() {
        every { openLink("https://twitter.com/ali-star-twitter") } returns Unit
    }

    fun clickOnTwitterLink() {
        composeContentTestRole.onNodeWithText("ali-star-twitter")
            .performSemanticsAction(SemanticsActions.OnClick)
    }

    fun checkOpenTwitterLinkGotCalled() {
        verify { openLink("https://twitter.com/ali-star-twitter") }
    }
}
