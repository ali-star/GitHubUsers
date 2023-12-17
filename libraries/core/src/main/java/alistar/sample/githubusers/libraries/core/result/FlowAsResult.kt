package alistar.sample.githubusers.libraries.core.result

import alistar.sample.githubusers.libraries.core.extensions.catchError
import kotlinx.coroutines.flow.flow

fun <T> wrapToResult(bloc: suspend () -> T) = flow {
    emit(Result.Loading)
    emit(Result.Success(bloc()))
}.catchError { emit(Result.Error(it)) }
