package alistar.sample.githubusers.features.searchapi.mapper

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.features.searchapi.model.UserItem

fun User.toView(): UserItem = UserItem(
    username = username,
    photoUrl = photoUrl,
)
