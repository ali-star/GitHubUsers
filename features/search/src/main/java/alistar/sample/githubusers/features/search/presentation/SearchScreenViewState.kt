package alistar.sample.githubusers.features.search.presentation

import alistar.sample.githubusers.features.searchapi.model.UserItem
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

data class SearchScreenViewState(
    val inputText: String = "",
    val lastSearchedQuery: String = "",
    val pagingData: Flow<PagingData<UserItem>>? = null,
    val users: StateFlow<List<UserItem>>,
    val isSearchBarFocused: Boolean = false
)
