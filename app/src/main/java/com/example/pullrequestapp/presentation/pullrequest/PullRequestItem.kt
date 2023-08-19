package com.example.pullrequestapp.presentation.pullrequest

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.pullrequestapp.R
import com.example.pullrequestapp.presentation.theme.Purple40

@Composable
fun PullRequestItem(
  title: String,
  createdAt: String,
  closedAt: String,
  userName: String,
  userImage: String,
  isMerged: Boolean,
  isExpanded: Boolean,
  onClick: () -> Unit,
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(elevation = 1.dp)
      .animateContentSize(
        animationSpec = tween(
          delayMillis = 50,
          easing = LinearOutSlowInEasing
        )
      )
      .padding(8.dp)
      .clickable(
        indication = null,
        interactionSource = remember {
          MutableInteractionSource()
        },
        onClick = onClick
      ),
  ) {
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        if (!isMerged) {
          Icon(
            painter = painterResource(id = R.drawable.ic_closed_pr),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.Red
          )
        } else {
          Icon(
            painter = painterResource(id = R.drawable.ic_merged_pr),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Purple40
          )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
          text = title,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          maxLines = if (isExpanded) Int.MAX_VALUE else 1,
          overflow = TextOverflow.Ellipsis,
          color = MaterialTheme.colorScheme.primary
        )
      }
      if (isExpanded) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "Created: $createdAt",
          color = MaterialTheme.colorScheme.secondary,
          modifier = Modifier.padding(start = 24.dp)
        )
        Text(
          text = "Closed: $closedAt",
          color = MaterialTheme.colorScheme.secondary,
          modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.fillMaxWidth()
        ) {
          Spacer(modifier = Modifier.size(24.dp))
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
}

@Preview
@Composable
private fun PreviewPullRequestItem() {
  PullRequestItem(
    title = "Example Pull Request",
    createdAt = "dummyDate",
    closedAt = "dummyDate",
    userName = "Example User",
    userImage = "https://picsum.photos/200",
    isMerged = true,
    isExpanded = false
  ) {}
}

