package com.example.pullrequestapp.presentation.pullrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PullRequestItem(
  title: String,
  createdAt: String,
  closedAt: String,
  userName: String,
  userImage: String
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(elevation = 1.dp)
      .padding(8.dp),
  ) {
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = "Created: $createdAt",
        color = MaterialTheme.colorScheme.secondary
      )
      Text(
        text = "Closed: $closedAt",
        color = MaterialTheme.colorScheme.secondary
      )
      Spacer(modifier = Modifier.height(8.dp))
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Image(
          painter = rememberAsyncImagePainter(model = userImage),
          contentDescription = "User Image",
          modifier = Modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            .border(1.dp, Color.Gray, CircleShape),
          contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = userName,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )
      }
    }
  }
}

@Preview
@Composable
private fun PreviewPullRequestItem() {
  PullRequestItem(
    title = "Example Pull Request",
    createdAt = "dummyDate",
    closedAt = "dummyDate",
    userName = "Example User",
    userImage = "https://picsum.photos/200"
  )
}

