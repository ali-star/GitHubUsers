package alistar.sample.githubusers.libraries.core.result

import alistar.sample.githubusers.libraries.core.extensions.catchError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.wrapToResult(): Flow<Result<T>> = flow {
    emit(Result.Loading)
    collect {
        emit(Result.Success(it))
    }
}.catchError { emit(Result.Error(it)) }
