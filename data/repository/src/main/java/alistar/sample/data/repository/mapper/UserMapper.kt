package alistar.sample.data.repository.mapper

import alistar.sample.githubusers.domain.model.User
import alistar.sample.data.repository.model.RepoUser as RepoUser

fun RepoUser.toDomain(): User = User(
    username = username,
    photoUrl = photoUrl
)
