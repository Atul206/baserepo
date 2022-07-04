package com.roadster.roam.basesetup.network

import com.roadster.roam.basesetup.utils.BaseTokenManager
import okhttp3.Request

const val AUTHORIZATION = "Authorization"
const val USER_AGENT = "User-Agent"
const val BEARER = "Bearer "
const val BASIC = "basic "

fun Request.Builder.addAuthorizationHeader(accessToken: BaseTokenManager) =
    addHeader(
        AUTHORIZATION,
        getFormattedBearerAuthToken(accessToken.accessToken ?: "")
    )

fun Request.Builder.addAuthorizationBasicHeader(accessToken: String) =
    addHeader(
        AUTHORIZATION,
        getFormattedBasicAuthToken(accessToken)
    )

fun Request.Builder.addUserAgentHeader(userAgent: String) =
    addHeader(
        USER_AGENT,
        userAgent
    )

fun getFormattedBearerAuthToken(accessToken: String) = "$BEARER$accessToken"

fun getFormattedBasicAuthToken(accessToken: String) = "$BASIC$accessToken"