package com.example.pullrequestapp.presentation.pullrequest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PullRequestScreen(viewModel: PullRequestViewModel) {

  val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing)

  SwipeRefresh(
    state = swipeRefreshState,
    onRefresh = viewModel::refresh,
    modifier = Modifier.fillMaxSize()
  ) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(
        count = viewModel.pullRequests.size,
        key = {
          viewModel.pullRequests[it].id
        }
      ) { index ->
        PullRequestItem(
          title = viewModel.pullRequests[index].title,
          createdAt = formatDate(viewModel.pullRequests[index].createdAt),
          closedAt = formatDate(viewModel.pullRequests[index].closedAt),
          userName = viewModel.pullRequests[index].user.login,
          userImage = viewModel.pullRequests[index].user.avatarUrl
        )
      }
    }
  }
}

fun formatDate(dateString: String): String {
  val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
  val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())

  val date = inputFormat.parse(dateString)
  return outputFormat.format(date ?: Date())
}