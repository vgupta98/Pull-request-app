package com.example.pullrequestapp.domain.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val HEADER_X_GITHUB_API_VERSION = "X-GitHub-Api-Version"
private const val HEADER_ACCEPT = "Accept"
private const val HEADER_AUTHORIZATION = "Authorization"

class HeadersInterceptor : Interceptor {
  override fun intercept(
    chain: Interceptor.Chain
  ): Response {
    val request: Request = chain.request()
    val newRequest: Request.Builder =
      request.newBuilder()
        .addHeader(HEADER_X_GITHUB_API_VERSION, "2022-11-28")
        .addHeader(HEADER_ACCEPT, "application/vnd.github+json")
        .addHeader(
          HEADER_AUTHORIZATION,
          "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"
        )
    return chain.proceed(newRequest.build())
  }
}
