package alistar.sample.githubusers.data.remote.datasource

import alistar.sample.data.repository.model.RepoUser
import alistar.sample.githubusers.data.remote.api.Apis
import alistar.sample.githubusers.data.remote.mapper.toRepo
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class UsersPagingSource(
    private val apis: Apis,
    private val query: String
) : PagingSource<Int, RepoUser>() {

    override fun getRefreshKey(state: PagingState<Int, RepoUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
                ?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)
                    ?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoUser> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apis.search(
                query = query,
                limit = params.loadSize,
                page = page
            )
            val users = response.items.map {
                it.toRepo()
            }
            val nextKey = (page + 1).let { if (it == params.key) null else it }
            val prevKey = if (page == STARTING_PAGE_INDEX) null else page
            LoadResult.Page(users, prevKey = prevKey, nextKey = nextKey)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
