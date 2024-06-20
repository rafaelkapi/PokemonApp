/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.di

import com.cactus.network.qualifiers.CommonsMoshiAdapters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
class CommonsMoshiAdapterModule {

    @Singleton
    @Provides
    @CommonsMoshiAdapters
    @IntoSet
    fun provideCommonsPlatformInterceptor(): JsonAdapter.Factory {
        return KotlinJsonAdapterFactory()
    }
}
