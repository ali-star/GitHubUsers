package alistar.sample.githubusers.features.searchapi.model

import alistar.sample.githubusers.domain.model.User

fun User.toView(): UserItem = UserItem(
    username = username,
    photoUrl = photoUrl,
)
