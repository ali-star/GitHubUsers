package alistar.sample.githubusers.domain.model

data class UserDetail(
    val username: String,
    val name: String,
    val photoUrl: String,
    val followersCount: Int,
    val followingCount: Int,
    val organizations: String?,
    val location: String?,
    val twitterUsername: String?,
    val blogUrl: String?
)
