package alistar.sample.githubusers.libraries.core

import alistar.sample.githubusers.libraries.core.extensions.toQuantityString
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class IntExtTest : TestCase() {

    private val robot = Robot()

    @Test
    fun test_1() {
        RUN_UNIT_TEST(robot) {
            GIVEN { value = 1 }
            WHEN { convert() }
            THEN { checkConvertedValueIs1Like() }
        }
    }

    @Test
    fun test_43() {
        RUN_UNIT_TEST(robot) {
            GIVEN { value = 43 }
            WHEN { convert() }
            THEN { checkConvertedValueIs43Likes() }
        }
    }

    @Test
    fun test_200() {
        RUN_UNIT_TEST(robot) {
            GIVEN { value = 200 }
            WHEN { convert() }
            THEN { checkConvertedValueIs200Likes() }
        }
    }

    @Test
    fun test_2000() {
        RUN_UNIT_TEST(robot) {
            GIVEN { value = 2000 }
            WHEN { convert() }
            THEN { checkConvertedValueIs2kLikes() }
        }
    }

    class Robot : BaseRobot() {

        var value: Int = 0
        private lateinit var converted: String

        fun convert() {
            converted = value.toQuantityString("Follower", "Followers")
        }

        fun checkConvertedValueIs1Like() {
            assertEquals("1 Follower", converted)
        }

        fun checkConvertedValueIs43Likes() {
            assertEquals("43 Followers", converted)
        }

        fun checkConvertedValueIs200Likes() {
            assertEquals("200 Followers", converted)
        }

        fun checkConvertedValueIs2kLikes() {
            assertEquals("2k Followers", converted)
        }
    }
}
