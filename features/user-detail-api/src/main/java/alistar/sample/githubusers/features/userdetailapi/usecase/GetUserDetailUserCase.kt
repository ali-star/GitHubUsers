package alistar.sample.githubusers.features.userdetailapi.usecase

import alistar.sample.githubusers.domain.interactor.GetUserDetailInteractor
import alistar.sample.githubusers.features.userdetailapi.item.UserDetailItem
import alistar.sample.githubusers.features.userdetailapi.mapper.toView
import alistar.sample.githubusers.libraries.core.result.Result
import alistar.sample.githubusers.libraries.core.result.wrapToResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserDetailUserCase @Inject constructor(
    private val getUserDetailInteractor: GetUserDetailInteractor
) {

    operator fun invoke(username: String): Flow<Result<UserDetailItem>> =
        getUserDetailInteractor(username).map { it.toView() }.wrapToResult()
}
