package alistar.sample.githubusers.features.userdetailapi.usecase

import alistar.sample.githubusers.domain.interactor.GetUserDetailInteractor
import alistar.sample.githubusers.features.userdetailapi.item.UserDetailItem
import alistar.sample.githubusers.features.userdetailapi.mapper.toView
import alistar.sample.githubusers.libraries.core.result.Result
import alistar.sample.githubusers.libraries.core.result.wrapToResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val getUserDetailInteractor: GetUserDetailInteractor
) {

    operator fun invoke(username: String): Flow<Result<UserDetailItem>> = wrapToResult {
        getUserDetailInteractor(username).toView()
    }
}
