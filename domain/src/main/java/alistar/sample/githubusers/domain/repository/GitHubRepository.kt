package alistar.sample.githubusers.domain.repository

import alistar.sample.githubusers.domain.Result
import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.model.UserDetail
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun getUsersPagingSource(): Flow<PagingData<User>>

    fun getUserDetail(username: String): Flow<Result<UserDetail>>
}
