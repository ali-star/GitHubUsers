package alistar.sample.data.repository.mapper

import alistar.sample.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.AND
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserDetailMapperTest : TestCase() {

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
                userDetail = UserDetail(
                    username = "ali-star",
                    name = "Ali Mohseni Rad",
                    photoUrl = "photoUrl",
                    followingCount = 10,
                    followersCount = 15,
                    organizations = "organization",
                    location = "Amsterdam, Netherlands",
                    twitterUsername = "@ali-star",
                    blogUrl = "alimohsenirad.ir"
                )
            }
            WHEN { mapToDomain() }
            THEN { checkRepoUserDetailMappedSuccessfully() }
        }
    }

    private class Robot : BaseRobot() {

        lateinit var repoUserDetail: RepoUserDetail
        lateinit var userDetail: UserDetail
        private lateinit var mappedUserDetail: UserDetail

        fun mapToDomain() {
            mappedUserDetail = repoUserDetail.toDomain()
        }

        fun checkRepoUserDetailMappedSuccessfully() {
            assertEquals(repoUserDetail.username, mappedUserDetail.username)
            assertEquals(repoUserDetail.name, mappedUserDetail.name)
            assertEquals(repoUserDetail.photoUrl, mappedUserDetail.photoUrl)
            assertEquals(repoUserDetail.followingCount, mappedUserDetail.followingCount)
            assertEquals(repoUserDetail.followersCount, mappedUserDetail.followersCount)
            assertEquals(repoUserDetail.company, mappedUserDetail.organizations)
            assertEquals(repoUserDetail.location, mappedUserDetail.location)
            assertEquals(repoUserDetail.twitterUsername, mappedUserDetail.twitterUsername)
            assertEquals(repoUserDetail.blog, mappedUserDetail.blogUrl)
        }
    }
}
