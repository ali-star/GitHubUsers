package alistar.sample.githubusers.data.remote.mapper

import alistar.sample.githubusers.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.data.remote.entity.UserDetailEntity

fun UserDetailEntity.toRepo(): RepoUserDetail = RepoUserDetail(
    username = login,
    name = if (name.isNullOrEmpty()) null else name,
    photoUrl = if (gravatar.isNullOrEmpty()) avatar else "https://www.gravatar.com/avatar/$gravatar",
    followersCount = followers,
    followingCount = following,
    company = if (company.isNullOrEmpty()) null else company,
    location = if (location.isNullOrEmpty()) null else location,
    twitterUsername = if (twitterUsername.isNullOrEmpty()) null else twitterUsername,
    blog = if (blog.isNullOrEmpty()) null else blog,
)
