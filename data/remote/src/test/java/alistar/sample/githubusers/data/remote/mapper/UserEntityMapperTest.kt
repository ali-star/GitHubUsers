package alistar.sample.githubusers.data.remote.mapper

import alistar.sample.githubusers.data.repository.model.RepoUser
import alistar.sample.githubusers.data.remote.entity.UserEntity
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserEntityMapperTest : TestCase() {

    private val robot = Robot()

    @Test
    fun test_toDomain() {
        RUN_UNIT_TEST(robot) {
            GIVEN {
                repoUser = RepoUser(
                    username = "ali-star",
                    photoUrl = "photoUrl"
                )
            }
            AND {
                userEntity = UserEntity(
                    login = "ali-star",
                    avatar = "photoUrl",
                    gravatar = ""
                )
            }
            WHEN { mapToDomain() }
            THEN { checkRepoUserMappedToUser() }
        }
    }

    private class Robot : BaseRobot() {

        lateinit var repoUser: RepoUser
        lateinit var userEntity: UserEntity
        private lateinit var mappedUser: RepoUser

        fun mapToDomain() {
            mappedUser = userEntity.toRepo()
        }

        fun checkRepoUserMappedToUser() {
            assertEquals(userEntity.login, repoUser.username)
            assertEquals(userEntity.avatar, repoUser.photoUrl)
        }
    }
}
