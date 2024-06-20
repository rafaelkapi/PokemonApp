/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.extension

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit

fun buildMoshi(builder: Moshi.Builder.() -> Unit): Moshi {
    return Moshi.Builder().apply(builder).build()
}

infix fun Moshi.createNewBuild(builder: Moshi.Builder.() -> Unit): Moshi {
    return newBuilder().apply(builder).build()
}

fun buildOkHttpClient(builder: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    return OkHttpClient.Builder().apply(builder).build()
}

fun buildRetrofit(builder: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder().apply(builder).build()
}
