package alistar.sample.githubusers.features.search.presentaion.ui

import alistar.sample.githubusers.features.search.presentation.ui.SearchBar
import alistar.sample.githubusers.libraries.design.theme.GitHubUsersTheme
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UI_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class SearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val robot = Robot(composeTestRule)

    @Test
    fun test_unFocused() {
        RUN_UI_TEST(robot) {
            GIVEN { requestFocus = false }
            WHEN { createSearchBar() }
            THEN { checkSearchIconIsDisplayed() }
            AND { checkHintIsDisplayed() }
            AND { checkGitHubIconIsDisplayed() }
            AND { checkClearIconsIsNotDisplayed() }
        }
    }

    @Test
    fun test_focused() {
        RUN_UI_TEST(robot) {
            GIVEN { requestFocus = true }
            WHEN { createSearchBar() }
            THEN { checkSearchIconIsDisplayed() }
            AND { checkHintIsNotDisplayed() }
            AND { checkGitHubIconIsNotDisplayed() }
            AND { checkClearIconsIsDisplayed() }
        }
    }

    @Test
    fun test_focusedWithText() {
        RUN_UI_TEST(robot) {
            GIVEN { requestFocus = true }
            WHEN { createSearchBar() }
            AND { enterText("ali") }
            THEN { checkSearchIconIsDisplayed() }
            AND { checkHintIsNotDisplayed() }
            AND { checkGitHubIconIsNotDisplayed() }
            AND { checkClearIconsIsDisplayed() }
            AND { checkTextIsDisplayed("ali") }
        }
    }

    private class Robot(private val composeContentTestRule: ComposeContentTestRule) : BaseRobot() {

        var requestFocus = false

        fun createSearchBar() {
            composeContentTestRule.setContent {
                GitHubUsersTheme {
                    Surface {
                        val text by remember { mutableStateOf("") }
                        SearchBar(
                            inputText = text,
                            requestFocus = requestFocus
                        )
                    }
                }
            }
        }

        fun checkSearchIconIsDisplayed() {
            composeContentTestRule.onNodeWithContentDescription("searchIcon").assertIsDisplayed()
        }

        fun checkHintIsDisplayed() {
            composeContentTestRule.onNodeWithText("Type username for search").assertIsDisplayed()
        }

        fun checkHintIsNotDisplayed() {
            composeContentTestRule.onNodeWithText("Type username for search").assertDoesNotExist()
        }

        fun checkGitHubIconIsDisplayed() {
            composeContentTestRule.onNodeWithContentDescription("gitHubIcon").assertIsDisplayed()
        }

        fun checkGitHubIconIsNotDisplayed() {
            composeContentTestRule.onNodeWithContentDescription("gitHubIcon").assertDoesNotExist()
        }

        fun checkClearIconsIsDisplayed() {
            composeContentTestRule.onNodeWithContentDescription("clearIcon").assertIsDisplayed()
        }

        fun checkClearIconsIsNotDisplayed() {
            composeContentTestRule.onNodeWithContentDescription("clearIcon").assertDoesNotExist()
        }

        fun enterText(text: String) {
            composeContentTestRule.onNodeWithTag("searchTextField").performTextInput(text)
        }

        fun checkTextIsDisplayed(text: String) {
            composeContentTestRule.onNodeWithTag("searchTextField").performTextInput(text)
        }
    }
}
