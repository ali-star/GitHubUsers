package alistar.sample.githubusers.data.repository

import alistar.sample.githubusers.data.repository.datasource.GitHubDataSource
import alistar.sample.githubusers.data.repository.mapper.toDomain
import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.domain.repository.GitHubRepository
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GitHubRepositoryImpl(
    private val gitHubDataSource: GitHubDataSource
) : GitHubRepository {

    private val ioDispatcher = Dispatchers.IO

    override fun searchUsers(query: String): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(pageSize = 30, initialLoadSize = 30),
            pagingSourceFactory = { gitHubDataSource.searchUsers(query) }
        ).flow.map { data -> data.map { it.toDomain() } }

    override fun getUserDetail(username: String): Flow<UserDetail> = flow {
        val userDetail = gitHubDataSource.getUserDetail(username)
        emit(userDetail.toDomain())
    }.flowOn(ioDispatcher)
}
