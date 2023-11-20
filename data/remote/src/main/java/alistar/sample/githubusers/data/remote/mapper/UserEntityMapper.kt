package alistar.sample.githubusers.data.remote.mapper

import alistar.sample.githubusers.data.repository.model.RepoUser
import alistar.sample.githubusers.data.remote.entity.UserEntity

fun UserEntity.toRepo(): RepoUser = RepoUser(
    username = login,
    photoUrl = if (gravatar.isNullOrEmpty()) avatar else "https://www.gravatar.com/avatar/$gravatar"
)
