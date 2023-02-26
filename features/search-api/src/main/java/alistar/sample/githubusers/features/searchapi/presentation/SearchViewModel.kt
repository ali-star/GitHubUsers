package alistar.sample.githubusers.features.searchapi.presentation

import androidx.lifecycle.ViewModel

abstract class SearchViewModel : ViewModel() {

    abstract fun searchUsers(query: String)
}
