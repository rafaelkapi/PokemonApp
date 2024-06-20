/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.di

import android.content.Context
import com.cactus.commons.extensions.isDebuggable
import com.cactus.network.qualifiers.CommonsInterceptors
import com.cactus.network.interceptor.CommonsBearerTokenInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level

@Module
abstract class CommonsNetworkInterceptorsModule {

    @Singleton
    @Binds
    @CommonsInterceptors
    @IntoSet
    abstract fun bindCommonsBearerTokenInterceptor(
        commonsInterceptor: CommonsBearerTokenInterceptor
    ): Interceptor

    @Module
    companion object {

        @Singleton
        @JvmStatic
        @Provides
        @CommonsInterceptors
        @IntoSet
        fun providesLoggerInterceptor(
            context: Context
        ): Interceptor {
            val logLevel = if (context.isDebuggable()) Level.BODY else Level.NONE
            return HttpLoggingInterceptor().apply {
                level = logLevel
            }
        }
    }
}
