package com.example.pullrequestapp.data.repository

import com.example.pullrequestapp.data.network.PullRequestService
import com.example.pullrequestapp.domain.extension.parse
import com.example.pullrequestapp.domain.model.PullRequestResponse
import com.example.pullrequestapp.domain.network.DataState
import com.example.pullrequestapp.domain.repository.PullRequestRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PullRequestRepositoryImpl @Inject constructor(
  private val pullRequestService: PullRequestService,
  private val ioDispatcher: CoroutineDispatcher
) : PullRequestRepository {
  override fun getAllPullRequests(): Flow<DataState<List<PullRequestResponse>>> {
    return flow {
      try {
        emit(DataState.Loading())
        emit(
          DataState.Success(
            pullRequestService.getAllPullRequests().parse()
          )
        )
      } catch (e: Exception) {
        emit(DataState.Error(e))
      }
    }.flowOn(ioDispatcher)
  }
}