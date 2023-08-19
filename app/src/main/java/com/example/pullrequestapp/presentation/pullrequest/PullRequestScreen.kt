package com.example.pullrequestapp.presentation.pullrequest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PullRequestScreen(viewModel: PullRequestViewModel) {
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(
      count = viewModel.pullRequests.size,
      key = {
        viewModel.pullRequests[it]
      }
    ) {
      Text(text = viewModel.pullRequests[it].toString())
    }
  }
}