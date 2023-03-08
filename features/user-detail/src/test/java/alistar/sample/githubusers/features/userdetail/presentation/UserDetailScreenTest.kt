package alistar.sample.githubusers.features.userdetail.presentation

import alistar.sample.githubusers.libraries.test.dsl.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class UserDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val robot = UserDetailScreenRobot(composeTestRule)

    @Test
    fun test_loadingState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockGetUserDetailUseCaseWithLoadingResult() }
            WHEN { createViewModel() }
            AND { createUserDetailScreen() }
            THEN { checkLoadingStateIsDisplayed() }
            AND { checkErrorStateIsNotDisplayed() }
            AND { checkResultStateIsNotDisplayed() }
            AND { checkTopBarIsDisplayed() }
        }
    }

    @Test
    fun test_errorState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockGetUserDetailUseCaseWithFailureResult() }
            WHEN { createViewModel() }
            AND { createUserDetailScreen() }
            THEN { checkErrorStateIsDisplayed() }
            AND { checkLoadingStateIsNotDisplayed() }
            AND { checkResultStateIsNotDisplayed() }
            AND { checkTopBarIsDisplayed() }
        }
    }

    @Test
    fun test_resultState() {
        RUN_UI_TEST(robot) {
            GIVEN { mockGetUserDetailUseCaseWithSuccessResult() }
            WHEN { createViewModel() }
            AND { createUserDetailScreen() }
            THEN { checkResultStateIsDisplayed() }
            AND { checkLoadingStateIsNotDisplayed() }
            AND { checkErrorStateIsNotDisplayed() }
            AND { checkTopBarIsDisplayed() }
        }
    }

    @Test
    fun test_navigateBack() {
        RUN_UI_TEST(robot) {
            GIVEN { mockGetUserDetailUseCaseWithSuccessResult() }
            WHEN { createViewModel() }
            AND { createUserDetailScreen() }
            AND { clickOnBackButton() }
            THEN { checkBackButtonIsClicked() }
        }
    }

    @Test
    fun test_openTwitterLink() {
        RUN_UI_TEST(robot) {
            GIVEN { mockOpenTwitterLinkFunction() }
            AND { mockGetUserDetailUseCaseWithSuccessResult() }
            WHEN { createViewModel() }
            AND { createUserDetailScreen() }
            AND { clickOnTwitterLink() }
            THEN { checkOpenTwitterLinkGotCalled() }
        }
    }

    @Test
    fun test_openBlogLink() {
        RUN_UI_TEST(robot) {
            GIVEN { mockOpenBlogLinkFunction() }
            AND { mockGetUserDetailUseCaseWithSuccessResult() }
            WHEN { createViewModel() }
            AND { createUserDetailScreen() }
            AND { clickOnBlogLink() }
            THEN { checkOpenBlogLinkGotCalled() }
        }
    }
}