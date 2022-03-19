package alistar.sample.githubusers.data.remote.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("gravatar_id")
    val gravatar: String?
)
