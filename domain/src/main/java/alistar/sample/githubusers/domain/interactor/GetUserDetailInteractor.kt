package alistar.sample.githubusers.domain.interactor

import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailInteractor @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {

    operator fun invoke(username: String): Flow<UserDetail> =
        gitHubRepository.getUserDetail(username)
}
