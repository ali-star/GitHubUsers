package alistar.sample.githubusers.features.search.mapper

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.features.search.item.UserItem
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserItemMapperTest : TestCase() {

    private val robot = Robot()

    @Test
    fun test_mapToView() {
        RUN_UNIT_TEST(robot) {
            GIVEN {
                user = User(
                    username = "ali-star",
                    photoUrl = "photoUrl"
                )
            }
            AND {
                userItem = UserItem(
                    username = "ali-star",
                    photoUrl = "photoUrl"
                )
            }
            WHEN { mapToDomain() }
            THEN { checkUserMappedSuccessfully() }
        }
    }

    private class Robot : BaseRobot() {

        lateinit var userItem: UserItem
        lateinit var user: User
        private lateinit var mappedUserItem: UserItem

        fun mapToDomain() {
            mappedUserItem = user.toView()
        }

        fun checkUserMappedSuccessfully() {
            assertEquals(userItem.username, user.username)
            assertEquals(userItem.photoUrl, user.photoUrl)
        }
    }
}
