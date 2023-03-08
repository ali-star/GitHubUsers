package alistar.sample.githubusers.features.search.presentaion

import alistar.sample.githubusers.features.search.presentation.SearchScreen
import alistar.sample.githubusers.features.search.presentation.SearchScreenViewModel
import alistar.sample.githubusers.features.searchapi.model.UserItem
import alistar.sample.githubusers.features.searchapi.usecase.SearchUsersUseCase
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.exception.TestException
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn

@OptIn(ExperimentalTestApi::class)
class SearchScreenRobot(private val composeContentTestRule: ComposeContentTestRule) : BaseRobot() {

    private val searchUsersUseCase: SearchUsersUseCase = mockk()
    private lateinit var viewModel: SearchScreenViewModel
    private val navigateToUserDetail: (username: String) -> Unit = mockk()
    private lateinit var pagingSource: TestPagingSource

    fun mockSearchUsersUseCase(
        delay: Long = 0,
        emitEmptyResult: Boolean = false,
        returnErrorOnFirstPage: Boolean = false,
        returnErrorOnSecondPage: Boolean = false
    ) {
        pagingSource = TestPagingSource(
            responseDelay = delay,
            emitEmptyResult = emitEmptyResult,
            returnErrorOnFirstPage = returnErrorOnFirstPage,
            returnErrorOnSecondPage = returnErrorOnSecondPage
        )
        every { searchUsersUseCase(any()) } answers {
            Pager(
                config = PagingConfig(pageSize = 30),
                pagingSourceFactory = { pagingSource }
            ).flow.flowOn(Dispatchers.IO)
        }
    }

    fun mockNavigateToUserDetailFunction() {
        every { navigateToUserDetail(any()) } returns Unit
    }

    fun createViewModel() {
        viewModel = SearchScreenViewModel(searchUsersUseCase)
    }

    fun createSearchScreen() {
        composeContentTestRule.setContent {
            Surface {
                SearchScreen(
                    searchScreenViewModel = viewModel,
                    navigateToUserDetail = navigateToUserDetail
                )
            }
        }
    }

    fun checkSearchBarIsDisplayed() {
        composeContentTestRule.onNodeWithTag("searchBar").assertIsDisplayed()
    }

    fun checkInitialStateIsDisplayedIsDisplayed() {
        composeContentTestRule.onNodeWithContentDescription("initialStatePlaceHolderImage")
            .assertIsDisplayed()
        composeContentTestRule.onNodeWithText("Start search").assertIsDisplayed()
        composeContentTestRule.onNodeWithTag("searchButton").assertIsDisplayed()
    }

    fun search(text: String) {
        composeContentTestRule.onNodeWithTag("searchTextField").performTextInput(text)
    }

    fun waitTillLoadingStateIsDisplayed() {
        composeContentTestRule.waitUntilExactlyOneExists(
            matcher = hasTestTag("usersListPlaceHolder"),
            timeoutMillis = 5000,
        )
    }

    fun waitTillUsersListStateIsDisplayed() {
        composeContentTestRule.waitUntilExactlyOneExists(
            matcher = hasTestTag("usersList"),
            timeoutMillis = 5000,
        )
    }

    fun waitTillUsersNotFoundStateIsDisplayed() {
        composeContentTestRule.waitUntilExactlyOneExists(
            matcher = hasTestTag("usersNotFoundState"),
            timeoutMillis = 5000,
        )
        composeContentTestRule.onNodeWithContentDescription("noUsersFoundPlaceHolderImage")
            .assertIsDisplayed()
        composeContentTestRule.onNodeWithText("No users found").assertIsDisplayed()
    }

    fun waitTillErrorStateIsDisplayed() {
        composeContentTestRule.waitUntilExactlyOneExists(
            matcher = hasTestTag("errorState"),
            timeoutMillis = 5000,
        )
        composeContentTestRule.onNodeWithText("Error").assertIsDisplayed()
        composeContentTestRule.onNodeWithText("Please check your internet connection")
            .assertIsDisplayed()
        composeContentTestRule.onNodeWithText("TAP TO RETRY").assertIsDisplayed()
    }

    fun checkListWithErrorStateIsDisplayed() {
        composeContentTestRule.waitUntilExactlyOneExists(
            matcher = hasText("Error getting new users"),
            timeoutMillis = 5000,
        )
    }

    fun clickOnUser() {
        composeContentTestRule.onNodeWithText("User 1").performClick()
    }

    fun checkNavigateToUserDetailCalled() {
        verify { navigateToUserDetail(any()) }
    }

    private class TestPagingSource(
        private val responseDelay: Long,
        private val emitEmptyResult: Boolean,
        private val returnErrorOnFirstPage: Boolean,
        private val returnErrorOnSecondPage: Boolean,
    ) : PagingSource<Int, UserItem>() {

        override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
            val key = params.key ?: 0
            val users = if (emitEmptyResult) {
                listOf()
            } else {
                buildList {
                    repeat(5) {
                        add(UserItem(username = "User $it", photoUrl = "photoUrl"))
                    }
                }
            }
            val nextKey = if (key == 2) null else key + 1
            delay(responseDelay)
            if (returnErrorOnFirstPage || nextKey == 2 && returnErrorOnSecondPage) {
                return LoadResult.Error(TestException)
            }
            return LoadResult.Page(users, null, nextKey)
        }
    }
}
