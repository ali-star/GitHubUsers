package alistar.sample.githubusers.domain.interactor

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.repository.GitHubRepository
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUsersInteractor @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {

    operator fun invoke(query: String): Flow<PagingData<User>> = gitHubRepository.searchUsers(query)

    suspend fun getUsers(query: String, page: Int): List<User> = gitHubRepository.search(query, page)
}
