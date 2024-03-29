package alistar.sample.githubusers.domain.repository

import alistar.sample.githubusers.domain.model.User
import alistar.sample.githubusers.domain.model.UserDetail
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun searchUsers(query: String): Flow<PagingData<User>>

    suspend fun getUserDetail(username: String): UserDetail
}
