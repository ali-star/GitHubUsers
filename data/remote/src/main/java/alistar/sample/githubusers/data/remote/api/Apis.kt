package alistar.sample.githubusers.data.remote.api

import alistar.sample.githubusers.data.remote.entity.GitHubPagedResponse
import alistar.sample.githubusers.data.remote.entity.UserDetailEntity
import alistar.sample.githubusers.data.remote.entity.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apis {

    @GET("/search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("pre_page") limit: Int,
        @Query("page") page: Int
    ): GitHubPagedResponse<UserEntity>

    @GET("/users/{username}")
    suspend fun userDetail(
        @Path("username") username: String
    ): UserDetailEntity
}
