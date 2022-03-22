package alistar.sample.githubusers.features.userdetail.presentation

import alistar.sample.githubusers.features.userdetail.item.UserDetailItem

sealed class UserDetailScreenViewState {

    object Loading : UserDetailScreenViewState()
    object Error : UserDetailScreenViewState()
    data class Result(val userDetailItem: UserDetailItem) : UserDetailScreenViewState()
}
