package alistar.sample.githubusers.data.remote.mapper

import alistar.sample.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.data.remote.entity.UserDetailEntity

fun UserDetailEntity.toRepo(): RepoUserDetail = RepoUserDetail(
    username = login,
    name = name,
    photoUrl = if (gravatar.isNullOrEmpty()) avatar else "https://www.gravatar.com/avatar/$gravatar",
    followersCount = followers,
    followingCount = following,
    organizations = company,
    location = location,
    twitterUsername = twitterUsername,
    blogUrl = blog
)
