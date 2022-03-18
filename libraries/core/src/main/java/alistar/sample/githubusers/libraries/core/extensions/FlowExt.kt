package alistar.sample.githubusers.libraries.core.extensions

import alistar.sample.githubusers.libraries.core.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch

/**
 * This method is used to print errors occurred in the flow to the logcat for easier debugging
 */
fun <T> Flow<T>.catchError(action: suspend FlowCollector<T>.(Throwable) -> Unit) = this.catch {
    if (BuildConfig.DEBUG) {
        it.printStackTrace()
    }
    action(it)
}
