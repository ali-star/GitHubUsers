package alistar.sample.githubusers.data.remote.mapper

import alistar.sample.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.data.remote.entity.UserDetailEntity
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserDetailEntityMapperTest : TestCase() {

    private val robot = Robot()

    @Test
    fun test_mapToDomain() {
        RUN_UNIT_TEST(robot) {
            GIVEN {
                repoUserDetail = RepoUserDetail(
                    username = "ali-star",
                    name = "Ali Mohseni Rad",
                    photoUrl = "photoUrl",
                    followingCount = 10,
                    followersCount = 15,
                    company = "organization",
                    location = "Amsterdam, Netherlands",
                    twitterUsername = "@ali-star",
                    blog = "alimohsenirad.ir"
                )
            }
            AND {
                userDetailEntity = UserDetailEntity(
                    login = "ali-star",
                    name = "Ali Mohseni Rad",
                    avatar = "photoUrl",
                    gravatar = "",
                    following = 10,
                    followers = 15,
                    company = "organization",
                    location = "Amsterdam, Netherlands",
                    twitterUsername = "@ali-star",
                    blog = "alimohsenirad.ir"
                )
            }
            WHEN { mapToDomain() }
            THEN { checkRepoUserDetailMappedSuccessfully() }
        }
    }

    private class Robot : BaseRobot() {

        lateinit var repoUserDetail: RepoUserDetail
        lateinit var userDetailEntity: UserDetailEntity
        private lateinit var mappedUserDetail: RepoUserDetail

        fun mapToDomain() {
            mappedUserDetail = userDetailEntity.toRepo()
        }

        fun checkRepoUserDetailMappedSuccessfully() {
            assertEquals(userDetailEntity.login, mappedUserDetail.username)
            assertEquals(userDetailEntity.name, mappedUserDetail.name)
            assertEquals(userDetailEntity.avatar, mappedUserDetail.photoUrl)
            assertEquals(userDetailEntity.following, mappedUserDetail.followingCount)
            assertEquals(userDetailEntity.followers, mappedUserDetail.followersCount)
            assertEquals(userDetailEntity.company, mappedUserDetail.company)
            assertEquals(userDetailEntity.location, mappedUserDetail.location)
            assertEquals(userDetailEntity.twitterUsername, mappedUserDetail.twitterUsername)
            assertEquals(userDetailEntity.blog, mappedUserDetail.blog)
        }
    }
}
