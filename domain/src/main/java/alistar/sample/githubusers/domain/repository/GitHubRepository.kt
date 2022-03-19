package alistar.sample.githubusers.domain.repository

import alistar.sample.githubusers.domain.Result
import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.model.UserDetail
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun searchUsers(query: String): Flow<PagingData<User>>

    fun getUserDetail(username: String): Flow<Result<UserDetail>>
}
