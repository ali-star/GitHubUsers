package alistar.sample.githubusers.data.remote.entity

import com.google.gson.annotations.SerializedName

data class UserDetailEntity(

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("gravatar_id")
    val gravatar: String?,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("company")
    val company: String?,

    @SerializedName("blog")
    val blog: String?,

    @SerializedName("location")
    val location: String?,

    @SerializedName("twitter_username")
    val twitterUsername: String?
)
