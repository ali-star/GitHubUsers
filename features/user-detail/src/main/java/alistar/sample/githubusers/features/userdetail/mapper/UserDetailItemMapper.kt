package alistar.sample.githubusers.features.userdetail.mapper

import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.features.userdetail.item.UserDetailItem

fun UserDetail.toView(): UserDetailItem = UserDetailItem(
    username = username,
    name = name,
    photoUrl = photoUrl,
    followersCount = followersCount,
    followingCount = followingCount,
    company = organizations,
    location = location,
    twitter = twitterUsername,
    blog = blogUrl
)
