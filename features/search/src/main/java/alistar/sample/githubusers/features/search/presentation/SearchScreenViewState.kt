package alistar.sample.githubusers.features.search.presentation

import alistar.sample.githubusers.features.search.item.UserItem
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class SearchScreenViewState(
    val inputText: String = "",
    val lastSearchedQuery: String = "",
    val pagingData: Flow<PagingData<UserItem>>? = null,
    val isSearchBarFocused: Boolean = false
)
