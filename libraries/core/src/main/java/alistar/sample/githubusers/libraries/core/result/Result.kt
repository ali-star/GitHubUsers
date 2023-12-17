package alistar.sample.githubusers.libraries.core.result

sealed class Result<out R> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}
