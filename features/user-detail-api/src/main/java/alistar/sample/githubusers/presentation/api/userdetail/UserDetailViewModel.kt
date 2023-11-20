package alistar.sample.githubusers.presentation.api.userdetail

import androidx.lifecycle.ViewModel

abstract class UserDetailViewModel : ViewModel() {

    abstract val username: String

    abstract fun getUserDetail()
}
