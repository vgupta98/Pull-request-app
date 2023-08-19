package com.example.pullrequestapp.presentation.pullrequest

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pullrequestapp.domain.network.DataState.Error
import com.example.pullrequestapp.domain.network.DataState.Loading
import com.example.pullrequestapp.domain.network.DataState.Success
import com.example.pullrequestapp.domain.repository.PullRequestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
  private val pullRequestRepository: PullRequestRepository
) : ViewModel() {
  val pullRequests = mutableStateListOf<Long>()

  init {
    getAllPullRequests().onEach { dataState ->
      when (dataState) {
        is Error -> {
          Log.d("PullRequestViewModel", "PullRequestViewModel: ${dataState.error}")
        }

        is Loading -> {}
        is Success -> {
          Log.d("PullRequestViewModel", "PullRequestViewModel: ${dataState.data}")
          dataState.data?.let { fetchedPullRequests ->
            pullRequests.clear()
            pullRequests.addAll(
              fetchedPullRequests.map { it.id }
            )
          }
        }
      }
    }.launchIn(viewModelScope)
  }

  fun getAllPullRequests() = pullRequestRepository.getAllPullRequests()
}