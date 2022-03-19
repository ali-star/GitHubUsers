package alistar.sample.data.repository

import alistar.sample.data.repository.datasource.GitHubDataSource
import alistar.sample.data.repository.mapper.toDomain
import alistar.sample.githubusers.domain.Result
import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.model.UserDetail
import alistar.sample.githubusers.domain.repository.GitHubRepository
import alistar.sample.githubusers.libraries.core.extensions.catchError
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

    override fun getUsersPagingSource(): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { gitHubDataSource.getUsersPagingSource() }
        ).flow.map { data -> data.map { it.toDomain() } }

    override fun getUserDetail(username: String): Flow<Result<UserDetail>> = flow {
        emit(Result.Loading)
        val userDetail = gitHubDataSource.getUserDetail(username)
        emit(Result.Success(userDetail.toDomain()))
    }.flowOn(ioDispatcher).catchError { emit(Result.Error(it)) }
}
