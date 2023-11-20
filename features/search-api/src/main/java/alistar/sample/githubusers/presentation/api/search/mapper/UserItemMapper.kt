package alistar.sample.githubusers.presentation.api.search.mapper

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.presentation.api.search.model.UserItem

fun User.toView(): UserItem = UserItem(
    username = username,
    photoUrl = photoUrl,
)
