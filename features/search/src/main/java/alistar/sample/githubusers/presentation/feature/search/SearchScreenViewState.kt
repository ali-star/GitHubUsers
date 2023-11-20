package alistar.sample.githubusers.presentation.feature.search

import alistar.sample.githubusers.presentation.api.search.model.UserItem
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class SearchScreenViewState(
    val inputText: String = "",
    val lastSearchedQuery: String = "",
    val pagingData: Flow<PagingData<UserItem>>? = null,
    val isSearchBarFocused: Boolean = false
)
