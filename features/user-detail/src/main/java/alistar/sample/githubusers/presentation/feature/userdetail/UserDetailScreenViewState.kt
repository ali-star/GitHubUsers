package alistar.sample.githubusers.presentation.feature.userdetail

import alistar.sample.githubusers.presentation.api.userdetail.item.UserDetailItem

sealed class UserDetailScreenViewState {

    object Loading : UserDetailScreenViewState()
    object Error : UserDetailScreenViewState()
    data class Result(val userDetailItem: UserDetailItem) : UserDetailScreenViewState()
}
