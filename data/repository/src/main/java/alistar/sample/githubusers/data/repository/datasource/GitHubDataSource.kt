package alistar.sample.githubusers.data.repository.datasource

import alistar.sample.githubusers.data.repository.model.RepoUser
import alistar.sample.githubusers.data.repository.model.RepoUserDetail
import androidx.paging.PagingSource

interface GitHubDataSource {

    fun searchUsers(query: String): PagingSource<Int, RepoUser>

    suspend fun getUserDetail(username: String): RepoUserDetail
}
