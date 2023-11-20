package alistar.sample.githubusers.presentation.api.userdetail.mapper

import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.presentation.api.userdetail.item.UserDetailItem

fun UserDetail.toView(): UserDetailItem = UserDetailItem(
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
