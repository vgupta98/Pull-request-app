package com.example.pullrequestapp.presentation.pullrequest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pullrequestapp.R
import com.example.pullrequestapp.data.network.REPO_NAME
import com.example.pullrequestapp.data.network.USER_NAME
import com.example.pullrequestapp.presentation.theme.PurpleGrey80
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PullRequestScreen(viewModel: PullRequestViewModel) {

  val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing)
  var expandedIndex by remember {
    mutableStateOf<Int?>(null)
  }

  SwipeRefresh(
    state = swipeRefreshState,
    onRefresh = viewModel::refresh,
    modifier = Modifier.fillMaxSize()
  ) {
    if (viewModel.isLoading) {
      Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
          modifier = Modifier.align(Alignment.Center)
        )
      }
    } else {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(PurpleGrey80)
              .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Icon(
              painter = painterResource(id = R.drawable.ic_github),
              contentDescription = null,
              modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
              text = "$USER_NAME / "
            )
            Text(
              text = REPO_NAME,
              fontWeight = FontWeight.Bold,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }
        }
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
            userImage = viewModel.pullRequests[index].user.avatarUrl,
            isMerged = viewModel.pullRequests[index].mergedAt != null,
            isExpanded = expandedIndex == index
          ) {
            expandedIndex = if (expandedIndex != index)
              index
            else
              null
          }
        }
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