package alistar.sample.githubusers.data.remote.entity

import com.google.gson.annotations.SerializedName

data class GitHubPagedResponse<T : Any>(
    @SerializedName("items")
    val items: List<T>
)
