package alistar.sample.githubusers.domain.usecase

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.repository.GitHubRepository
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersPagingSourceUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {

    operator fun invoke(): Flow<PagingData<User>> = gitHubRepository.getUsersPagingSource()
}
