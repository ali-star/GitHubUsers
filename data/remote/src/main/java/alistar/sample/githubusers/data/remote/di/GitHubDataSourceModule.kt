package alistar.sample.githubusers.data.remote.di

import alistar.sample.data.repository.datasource.GitHubDataSource
import alistar.sample.githubusers.data.remote.api.Apis
import alistar.sample.githubusers.data.remote.datasource.GitHubDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubDataSourceModule {

    @Provides
    @Singleton
    fun provideGitHubDataSource(apis: Apis): GitHubDataSource = GitHubDataSourceImpl(apis)
}
