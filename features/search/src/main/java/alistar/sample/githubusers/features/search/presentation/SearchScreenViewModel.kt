package alistar.sample.githubusers.features.search.presentation

import alistar.sample.githubusers.domain.usecase.SearchUsersUseCase
import alistar.sample.githubusers.features.search.item.UserItem
import alistar.sample.githubusers.features.search.mapper.toView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchUseCase: SearchUsersUseCase
) : ViewModel() {

    private val viewModelState = MutableStateFlow(SearchScreenViewState())
    val uiState = viewModelState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = viewModelState.value
    )

    private var searchDebouncerJob: Job = Job()
    private var currentQuery = ""
    private val ioDispatcher = Dispatchers.IO

    fun onInputTextChanged(text: String) {
        viewModelState.update {
            it.copy(inputText = text)
        }
        search(text)
    }

    private fun search(query: String) {
        searchDebouncerJob.cancel()
        currentQuery = query

        if (query.isNotEmpty()) {
            searchDebouncerJob = viewModelScope.launch(ioDispatcher) {
                delay(timeMillis = 700)
                if (currentQuery != viewModelState.value.lastSearchedQuery) {
                    viewModelState.update { state ->
                        state.copy(lastSearchedQuery = query, pagingData = getPagingDataFlow(query))
                    }
                }
            }
        } else {
            viewModelState.update { state ->
                state.copy(lastSearchedQuery = "", pagingData = null)
            }
        }
    }

    private fun getPagingDataFlow(searchQuery: String): Flow<PagingData<UserItem>> =
        searchUseCase(searchQuery)
            .cachedIn(viewModelScope)
            .map { data -> data.map { it.toView() } }

    fun updateSearchBarState(isFocused: Boolean) {
        viewModelState.update {
            it.copy(isSearchBarFocused = isFocused)
        }
    }

    fun resetToInitialState() {
        viewModelState.update {
            it.copy(
                inputText = "",
                lastSearchedQuery = "",
                pagingData = null,
                isSearchBarFocused = false
            )
        }
    }
}
