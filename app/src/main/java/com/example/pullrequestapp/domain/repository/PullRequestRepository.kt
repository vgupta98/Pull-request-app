package com.example.pullrequestapp.domain.repository

import com.example.pullrequestapp.domain.model.PullRequestResponse
import com.example.pullrequestapp.domain.network.DataState
import kotlinx.coroutines.flow.Flow

interface PullRequestRepository {
  fun getAllPullRequests(): Flow<DataState<List<PullRequestResponse>>>
}