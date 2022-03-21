package alistar.sample.githubusers.data.remote.retrofilt

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(username: String, token: String) : Interceptor {

    private val credentials = Credentials.basic(username, token)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", credentials)
            .build()
        return chain.proceed(request)
    }
}
