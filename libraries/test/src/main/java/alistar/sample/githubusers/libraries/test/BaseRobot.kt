package alistar.sample.githubusers.libraries.test

/**
 * Each test Robot in our application (UI test Robot or Unit test Robot) should extend BaseRobot
 */
open class BaseRobot {
    /**
     * Use the method to setup stuff in your Robot class before each test run
     */
    open fun setup() {
        // no base implementation
    }

    open fun tearsDown() {
        // no base implementation
    }
}
