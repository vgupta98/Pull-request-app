package com.example.pullrequestapp.data.dependency

import com.example.pullrequestapp.data.network.PullRequestService
import com.example.pullrequestapp.data.repository.PullRequestRepositoryImpl
import com.example.pullrequestapp.domain.repository.PullRequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object PullRequestRepositoryModule {

  @ViewModelScoped
  @Provides
  fun providePullRequestRepositoryModule(
    pullRequestService: PullRequestService,
    ioDispatcher: CoroutineDispatcher
  ): PullRequestRepository = PullRequestRepositoryImpl(pullRequestService, ioDispatcher)
}