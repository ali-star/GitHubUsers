package alistar.sample.githubusers.presentation.feature.search

import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UI_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val robot = SearchScreenRobot(composeTestRule)

    @Test
    fun test_initialState() {
        RUN_UI_TEST(robot) {
            GIVEN { /* Nothing */ }
            WHEN { createViewModel() }
            AND { createSearchScreen() }
            THEN { checkSearchBarIsDisplayed() }
            AND { checkInitialStateIsDisplayedIsDisplayed() }
        }
    }

    @Test
    fun test_loadingState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockSearchUsersUseCase(delay = 2000) }
            WHEN { createViewModel() }
            AND { createSearchScreen() }
            AND { search("user") }
            THEN { checkSearchBarIsDisplayed() }
            AND { waitTillLoadingStateIsDisplayed() }
        }
    }

    @Test
    fun test_userListState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockSearchUsersUseCase() }
            AND { mockNavigateToUserDetailFunction() }
            WHEN { createViewModel() }
            AND { createSearchScreen() }
            AND { search("user") }
            THEN { checkSearchBarIsDisplayed() }
            AND { waitTillUsersListStateIsDisplayed() }
            AND { clickOnUser() }
            AND { checkNavigateToUserDetailCalled() }
        }
    }

    @Test
    fun test_usersNotFoundState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockSearchUsersUseCase(emitEmptyResult = true) }
            WHEN { createViewModel() }
            AND { createSearchScreen() }
            AND { search("user") }
            THEN { checkSearchBarIsDisplayed() }
            AND { waitTillUsersNotFoundStateIsDisplayed() }
        }
    }

    @Test
    fun test_errorState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockSearchUsersUseCase(returnErrorOnFirstPage = true) }
            WHEN { createViewModel() }
            AND { createSearchScreen() }
            AND { search("user") }
            THEN { checkSearchBarIsDisplayed() }
            AND { waitTillErrorStateIsDisplayed() }
        }
    }

    @Test
    fun test_errorWhenListHasDataState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockSearchUsersUseCase(returnErrorOnSecondPage = true) }
            WHEN { createViewModel() }
            AND { createSearchScreen() }
            AND { search("user") }
            THEN { checkSearchBarIsDisplayed() }
            AND { checkListWithErrorStateIsDisplayed() }
        }
    }
}
