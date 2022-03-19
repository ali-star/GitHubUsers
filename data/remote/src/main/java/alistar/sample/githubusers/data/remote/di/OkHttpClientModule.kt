package alistar.sample.githubusers.data.remote.di

import alistar.sample.githubusers.data.remote.retrofilt.AppOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClint(): OkHttpClient = AppOkHttpClient().create()
}
