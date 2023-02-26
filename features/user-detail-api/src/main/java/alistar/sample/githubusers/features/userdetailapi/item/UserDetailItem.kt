package alistar.sample.githubusers.features.userdetailapi.item

data class UserDetailItem(
    val username: String,
    val name: String?,
    val photoUrl: String,
    val followersCount: Int,
    val followingCount: Int,
    val company: String?,
    val location: String?,
    val twitter: String?,
    val blog: String?
)
