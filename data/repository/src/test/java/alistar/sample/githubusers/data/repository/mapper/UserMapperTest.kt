package alistar.sample.githubusers.data.repository.mapper

import alistar.sample.githubusers.data.repository.model.RepoUser
import alistar.sample.githubusers.data.repository.mapper.toDomain
import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserMapperTest : TestCase() {

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
                user = User(
                    username = "ali-star",
                    photoUrl = "photoUrl"
                )
            }
            WHEN { mapToDomain() }
            THEN { checkRepoUserMappedToUser() }
        }
    }

    private class Robot : BaseRobot() {

        lateinit var repoUser: RepoUser
        lateinit var user: User
        private lateinit var mappedUser: User

        fun mapToDomain() {
            mappedUser = repoUser.toDomain()
        }

        fun checkRepoUserMappedToUser() {
            assertEquals(user.username, repoUser.username)
            assertEquals(user.photoUrl, repoUser.photoUrl)
        }
    }
}
