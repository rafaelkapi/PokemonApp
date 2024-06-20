/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.interceptor

import com.cactus.network.NetworkEnvironments
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class CommonsBearerTokenInterceptor @Inject constructor(
) : Interceptor {

    private companion object {
        const val CONST_AUTHORIZATION = "Authorization"
        const val CONST_BEARER = "Bearer "
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newBuilder = request.newBuilder()
            newBuilder.apply {
                val authorization: String = CONST_BEARER + NetworkEnvironments.bearerToken
                header(CONST_AUTHORIZATION, authorization)
            }

        return chain.proceed(newBuilder.build())
    }
}
