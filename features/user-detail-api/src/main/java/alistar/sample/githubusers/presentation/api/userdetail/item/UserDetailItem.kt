package alistar.sample.githubusers.presentation.api.userdetail.item

data class UserDetailItem(
    val username: String,
    val name: String?,
    val photoUrl: String,
    val followersCount: Int,
    val followingCount: Int,
    val organizations: String?,
    val location: String?,
    val twitterUsername: String?,
    val blogUrl: String?
)
