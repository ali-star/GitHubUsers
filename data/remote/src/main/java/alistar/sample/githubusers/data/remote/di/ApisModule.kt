package alistar.sample.githubusers.data.remote.di

import alistar.sample.githubusers.data.remote.api.Apis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApisModule {

    @Provides
    @Singleton
    fun provideApis(retrofit: Retrofit): Apis = retrofit.create(Apis::class.java)
}
