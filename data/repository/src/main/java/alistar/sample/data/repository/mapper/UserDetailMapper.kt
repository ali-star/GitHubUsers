package alistar.sample.data.repository.mapper

import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.data.repository.model.RepoUserDetail as RepoUserDetail

fun RepoUserDetail.toDomain(): UserDetail = UserDetail(
    username = username,
    name = name,
    photoUrl = photoUrl,
    followersCount = followersCount,
    followingCount = followingCount,
    organizations = organizations,
    location = location,
    twitterUsername = twitterUsername,
    blogUrl = blogUrl
)
