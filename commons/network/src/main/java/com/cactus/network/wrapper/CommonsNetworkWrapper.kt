/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.wrapper

import androidx.annotation.VisibleForTesting
import com.cactus.network.NetworkEnvironments
import com.cactus.network.qualifiers.CommonsInterceptors
import com.cactus.network.qualifiers.CommonsNullOrEmptyConverter
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.cactus.network.extension.buildRetrofit
import com.cactus.network.extension.buildOkHttpClient
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface CommonsNetworkWrapper {
    fun buildRetrofit(): Retrofit
}

class CommonsNetworkWrapperImpl @Inject constructor(
    @CommonsInterceptors private val interceptors: Set<@JvmSuppressWildcards Interceptor>,
    private val moshi: Moshi,
    @CommonsNullOrEmptyConverter private val nullOrEmptyConverter: Converter.Factory,
) : CommonsNetworkWrapper {

    @VisibleForTesting
    lateinit var okHttpClient: OkHttpClient

    @VisibleForTesting
    lateinit var retrofit: Retrofit
    override fun buildRetrofit(): Retrofit {
        createRetrofit()
        return retrofit
    }

    @VisibleForTesting
    fun guardOkHttpClient() {
        if (::okHttpClient.isInitialized.not()) {
            createOkHttpClient()
        }
    }

    private fun createOkHttpClient() {
        okHttpClient = buildOkHttpClient {
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
            interceptors.forEach { interceptor ->
                addInterceptor(interceptor)
            }
        }
    }

    @VisibleForTesting
    fun guardRetrofit() {
        if (::retrofit.isInitialized.not()) {
            createRetrofit()
        }
    }

    private fun createRetrofit() {
        guardOkHttpClient()
        retrofit = buildRetrofit {
            client(okHttpClient)
            baseUrl(NetworkEnvironments.baseUrl)
            addConverterFactory(nullOrEmptyConverter)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }
    }
}
