package com.roadster.roam.basesetup.network.interceptor

import com.roadster.roam.basesetup.network.AUTHORIZATION
import com.roadster.roam.basesetup.network.BEARER
import com.roadster.roam.basesetup.network.HttpStatusCode
import com.roadster.roam.basesetup.utils.TokenRenewer
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AutoRetryAuthenticator
 constructor(
    private val tokenRenewer: TokenRenewer
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

        return if (response.code == HttpStatusCode.CODE_UNAUTHORIZED) {
            val oldToken = response.request.header(AUTHORIZATION)
                ?.replace(BEARER.trim(), "")
                ?.trim()
            tokenRenewer.executeRenew(oldToken)?.let { newToken ->
                response.newRequest(newToken)
            }
        } else null
    }

    private fun Response.newRequest(newToken: String?): Request? = newToken?.let {
        with(request.newBuilder()) {
            header(AUTHORIZATION, getFormattedToken(it))
            build()
        }
    }

    private fun getFormattedToken(token: String) = "$BEARER$token"
}