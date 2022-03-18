package alistar.sample.githubusers.domain.usecase

import alistar.sample.githubusers.domain.Result
import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {

    operator fun invoke(username: String): Flow<Result<UserDetail>> =
        gitHubRepository.getUserDetail(username)
}
