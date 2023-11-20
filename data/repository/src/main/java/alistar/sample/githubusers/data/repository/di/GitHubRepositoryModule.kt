package alistar.sample.githubusers.data.repository.di

import alistar.sample.githubusers.data.repository.GitHubRepositoryImpl
import alistar.sample.githubusers.data.repository.datasource.GitHubDataSource
import alistar.sample.githubusers.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubRepositoryModule {

    @Provides
    @Singleton
    fun provideGitHubRepository(gitHubDataSource: GitHubDataSource): GitHubRepository =
        GitHubRepositoryImpl(gitHubDataSource)
}
