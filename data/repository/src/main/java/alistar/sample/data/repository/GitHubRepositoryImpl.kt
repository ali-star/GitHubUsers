package alistar.sample.data.repository

import alistar.sample.data.repository.datasource.GitHubDataSource
import alistar.sample.data.repository.mapper.toDomain
import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.domain.repository.GitHubRepository
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GitHubRepositoryImpl(
    private val gitHubDataSource: GitHubDataSource,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : GitHubRepository {

    override fun searchUsers(query: String): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(pageSize = 30, initialLoadSize = 30),
            pagingSourceFactory = { gitHubDataSource.searchUsers(query) }
        ).flow.map { data -> data.map { it.toDomain() } }

    override suspend fun getUserDetail(username: String): UserDetail = withContext(dispatcher) {
        val userDetail = gitHubDataSource.getUserDetail(username)
        userDetail.toDomain()
    }
}
