package alistar.sample.githubusers.features.userdetailapi.mapper

import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.features.userdetailapi.item.UserDetailItem
import alistar.sample.githubusers.libraries.test.BaseRobot
import alistar.sample.githubusers.libraries.test.dsl.GIVEN
import alistar.sample.githubusers.libraries.test.dsl.RUN_UNIT_TEST
import alistar.sample.githubusers.libraries.test.dsl.THEN
import alistar.sample.githubusers.libraries.test.dsl.WHEN
import junit.framework.TestCase
import org.junit.Test

class UserDetailItemMapperTest : TestCase() {

    private val robot = Robot()

    @Test
    fun test_mapToView() {
        RUN_UNIT_TEST(robot) {
            GIVEN { mockUserDetail() }
            WHEN { mapToView() }
            THEN { checkUserDetailMappedSuccessfully() }
        }
    }

    private class Robot : BaseRobot() {

        private lateinit var userDetail: UserDetail
        private lateinit var userDetailItem: UserDetailItem

        fun mockUserDetail() {
            userDetail = UserDetail(
                username = "ali-star",
                name = "Ali",
                photoUrl = "photoUrl",
                followersCount = 6,
                followingCount = 9,
                organizations = "an organization",
                location = "Netherlands",
                twitterUsername = "ali-star",
                blogUrl = "blog",
            )
        }

        fun mapToView() {
            userDetailItem = userDetail.toView()
        }

        fun checkUserDetailMappedSuccessfully() {
            with(userDetailItem) {
                assertEquals(name, userDetail.name)
                assertEquals(photoUrl, userDetail.photoUrl)
                assertEquals(followersCount, userDetail.followersCount)
                assertEquals(followingCount, userDetail.followingCount)
                assertEquals(organizations, userDetail.organizations)
                assertEquals(location, userDetail.location)
                assertEquals(twitterUsername, userDetail.twitterUsername)
                assertEquals(blogUrl, userDetail.blogUrl)
            }
        }
    }
}