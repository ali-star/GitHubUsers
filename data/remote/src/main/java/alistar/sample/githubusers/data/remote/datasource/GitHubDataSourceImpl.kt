package alistar.sample.githubusers.data.remote.datasource

import alistar.sample.githubusers.data.repository.datasource.GitHubDataSource
import alistar.sample.githubusers.data.repository.model.RepoUser
import alistar.sample.githubusers.data.repository.model.RepoUserDetail
import alistar.sample.githubusers.data.remote.api.Apis
import alistar.sample.githubusers.data.remote.mapper.toRepo
import androidx.paging.PagingSource

class GitHubDataSourceImpl(private val apis: Apis) : GitHubDataSource {

    override fun searchUsers(query: String): PagingSource<Int, RepoUser> =
        UsersPagingSource(apis = apis, query = query)

    override suspend fun getUserDetail(username: String): RepoUserDetail =
        apis.userDetail(username).toRepo()
}
