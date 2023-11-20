package alistar.sample.githubusers.presentation.feature.userdetail

import alistar.sample.githubusers.presentation.feature.userdetail.exception.MissingUsernameException
import alistar.sample.githubusers.presentation.api.userdetail.UserDetailViewModel
import alistar.sample.githubusers.presentation.api.userdetail.usecase.GetUserDetailUseCase
import alistar.sample.githubusers.libraries.navigation.DestinationArgs
import alistar.sample.githubusers.libraries.core.result.Result
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : UserDetailViewModel() {

    override val username: String = savedStateHandle.get<String>(DestinationArgs.USERNAME)
        ?: throw MissingUsernameException

    private val viewModelState = MutableStateFlow<UserDetailScreenViewState>(
        UserDetailScreenViewState.Loading
    )

    val uiState = viewModelState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = viewModelState.value
    )

    init {
        getUserDetail()
    }

    override fun getUserDetail() {
        viewModelScope.launch {
            getUserDetailUseCase(username).collect { result ->
                val state = when (result) {
                    is Result.Error -> UserDetailScreenViewState.Error
                    is Result.Loading -> UserDetailScreenViewState.Loading
                    is Result.Success -> UserDetailScreenViewState.Result(result.data)
                }
                viewModelState.emit(state)
            }
        }
    }

    fun retry() {
        getUserDetail()
    }
}
