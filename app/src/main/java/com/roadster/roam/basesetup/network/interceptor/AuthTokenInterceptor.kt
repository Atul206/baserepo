package com.roadster.roam.basesetup.network.interceptor

import com.roadster.roam.basesetup.network.addAuthorizationHeader
import com.roadster.roam.basesetup.utils.BaseTokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val tokenManager: BaseTokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addAuthorizationHeader(tokenManager)
            .build()
        return chain.proceed(newRequest)
    }
}