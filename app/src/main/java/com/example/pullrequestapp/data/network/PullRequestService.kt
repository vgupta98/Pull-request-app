package com.example.pullrequestapp.data.network

import com.example.pullrequestapp.domain.model.PullRequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullRequestService {

  @GET("/repos/{username}/{reponame}/pulls")
  suspend fun getAllPullRequests(
    @Path("username") username: String = "mukeshsolanki",
    @Path("reponame") reponame: String = "android-otpview-pinview",
    @Query("state") state: String = "closed",
  ): Response<List<PullRequestResponse>>
}