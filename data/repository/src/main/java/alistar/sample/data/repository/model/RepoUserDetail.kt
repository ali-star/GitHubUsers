package alistar.sample.data.repository.model

data class RepoUserDetail(
    val username: String,
    val photoUrl: String,
    val followersCount: Int,
    val followingCount: Int,
    val organizations: String,
    val location: String,
    val twitterUsername: String,
    val blogUrl: String
)
