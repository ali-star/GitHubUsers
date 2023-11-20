package alistar.sample.githubusers.data.repository.mapper

import alistar.sample.githubusers.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.domain.model.UserDetail

fun RepoUserDetail.toDomain(): UserDetail = UserDetail(
    username = username,
    name = name,
    photoUrl = photoUrl,
    followersCount = followersCount,
    followingCount = followingCount,
    organizations = company,
    location = location,
    twitterUsername = twitterUsername,
    blogUrl = blog
)
