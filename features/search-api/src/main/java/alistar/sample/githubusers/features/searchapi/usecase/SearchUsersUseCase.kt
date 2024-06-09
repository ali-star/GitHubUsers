package alistar.sample.githubusers.features.searchapi.usecase

import alistar.sample.githubusers.domain.interactor.SearchUsersInteractor
import alistar.sample.githubusers.features.searchapi.model.UserItem
import alistar.sample.githubusers.features.searchapi.model.toView
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val searchUsersInteractor: SearchUsersInteractor
) {

    operator fun invoke(query: String): Flow<PagingData<UserItem>> =
        searchUsersInteractor(query).map { data -> data.map { it.toView() } }

    suspend fun getUsers(query: String, page: Int): List<UserItem> =
        searchUsersInteractor.getUsers(query, page).map { it.toView() }
}
