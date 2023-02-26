package alistar.sample.githubusers.features.userdetailapi.presentation

import androidx.lifecycle.ViewModel

abstract class UserDetailViewModel : ViewModel() {

    abstract val username: String

    abstract fun getUserDetail()
}
