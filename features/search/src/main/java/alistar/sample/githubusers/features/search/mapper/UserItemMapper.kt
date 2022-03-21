package alistar.sample.githubusers.features.search.mapper

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.features.search.item.UserItem

fun User.toView(): UserItem = UserItem(
    username = username,
    photoUrl = photoUrl
)
