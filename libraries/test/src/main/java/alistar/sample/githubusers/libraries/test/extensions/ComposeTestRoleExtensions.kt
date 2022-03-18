package alistar.sample.githubusers.libraries.test.extensions

import androidx.compose.ui.test.junit4.ComposeTestRule

/**
 * Used for waiting for a successful assertion, Usually used in the UI tests to waiting
 * for a view to be displayed or gone.
 * @param timeoutMillis: timeout in milliseconds
 * @param block: the block to be executed
 */
fun ComposeTestRule.waitForIt(timeoutMillis: Long = 5000, block: () -> Unit) {
    waitUntil(timeoutMillis = timeoutMillis) {
        try {
            block()
            true
        } catch (ignored: AssertionError) {
            false
        }
    }
}
