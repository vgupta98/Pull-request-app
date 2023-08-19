package com.example.pullrequestapp.data.network

import com.example.pullrequestapp.domain.model.PullRequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val USER_NAME = "mukeshsolanki"
const val REPO_NAME = "android-otpview-pinview"

interface PullRequestService {

  @GET("/repos/{username}/{reponame}/pulls")
  suspend fun getAllPullRequests(
    @Path("username") username: String = USER_NAME,
    @Path("reponame") reponame: String = REPO_NAME,
    @Query("state") state: String = "closed",
  ): Response<List<PullRequestResponse>>
}