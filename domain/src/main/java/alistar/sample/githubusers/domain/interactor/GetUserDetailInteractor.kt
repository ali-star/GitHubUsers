package alistar.sample.githubusers.domain.interactor

import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.domain.repository.GitHubRepository
import javax.inject.Inject

class GetUserDetailInteractor @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {

    suspend operator fun invoke(username: String): UserDetail =
        gitHubRepository.getUserDetail(username)
}
