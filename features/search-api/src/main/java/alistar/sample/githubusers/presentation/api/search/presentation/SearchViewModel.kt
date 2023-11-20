package alistar.sample.githubusers.presentation.api.search.presentation

import androidx.lifecycle.ViewModel

abstract class SearchViewModel : ViewModel() {

    abstract fun search(query: String)
}
