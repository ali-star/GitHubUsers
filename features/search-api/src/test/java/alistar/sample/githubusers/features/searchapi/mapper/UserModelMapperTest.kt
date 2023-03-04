package alistar.sample.githubusers.features.searchapi.mapper

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.features.searchapi.model.UserItem
import alistar.sample.githubusers.features.searchapi.model.toView
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserModelMapperTest : TestCase() {

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
                userModel = UserItem(
                    username = "ali-star",
                    photoUrl = "photoUrl"
                )
            }
            WHEN { mapToDomain() }
            THEN { checkUserMappedSuccessfully() }
        }
    }

    private class Robot : BaseRobot() {

        lateinit var userModel: UserItem
        lateinit var user: User
        private lateinit var mappedUserModel: UserItem

        fun mapToDomain() {
            mappedUserModel = user.toView()
        }

        fun checkUserMappedSuccessfully() {
            assertEquals(userModel.username, user.username)
            assertEquals(userModel.photoUrl, user.photoUrl)
        }
    }
}
