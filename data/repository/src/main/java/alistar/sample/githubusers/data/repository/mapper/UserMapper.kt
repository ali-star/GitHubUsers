package alistar.sample.githubusers.data.repository.mapper

import alistar.sample.githubusers.data.repository.model.RepoUser
import alistar.sample.githubusers.domain.model.User

fun RepoUser.toDomain(): User = User(
    username = username,
    photoUrl = photoUrl
)
