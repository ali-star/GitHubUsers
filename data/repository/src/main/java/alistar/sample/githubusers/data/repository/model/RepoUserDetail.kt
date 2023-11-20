package alistar.sample.githubusers.data.repository.model

data class RepoUserDetail(
    val username: String,
    val name: String?,
    val photoUrl: String,
    val followersCount: Int,
    val followingCount: Int,
    val company: String?,
    val location: String?,
    val twitterUsername: String?,
    val blog: String?
)
