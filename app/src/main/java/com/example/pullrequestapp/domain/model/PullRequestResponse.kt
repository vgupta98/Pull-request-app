package com.example.pullrequestapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PullRequestResponse(
  val body: String?,
  @Json(name = "closed_at")
  val closedAt: String,
  @Json(name = "created_at")
  val createdAt: String,
  val id: Long,
  @Json(name = "merged_at")
  val mergedAt: String?,
  val title: String,
  @Json(name = "updated_at")
  val updatedAt: String?,
  val url: String,
  val user: User
)

@JsonClass(generateAdapter = true)
data class User(
  val login: String,
  @Json(name = "avatar_url")
  val avatarUrl: String,
  val id: Long,
  val type: String,
)