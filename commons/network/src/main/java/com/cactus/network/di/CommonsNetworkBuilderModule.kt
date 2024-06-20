/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.di

import com.cactus.network.converter.NullOrEmptyConverter
import com.cactus.network.qualifiers.CommonsMoshi
import com.cactus.network.qualifiers.CommonsMoshiAdapters
import com.cactus.network.qualifiers.CommonsNullOrEmptyConverter
import com.cactus.network.extension.buildMoshi
import com.cactus.network.wrapper.CommonsNetworkWrapper
import com.cactus.network.wrapper.CommonsNetworkWrapperImpl
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        CommonsNetworkInterceptorsModule::class,
        CommonsMoshiAdapterModule::class,
    ]
)
abstract class CommonsNetworkBuilderModule {

    @Singleton
    @Binds
    @CommonsNullOrEmptyConverter
    abstract fun bindNullOrEmptyConverter(default: NullOrEmptyConverter): Converter.Factory

    @Singleton
    @Binds
    abstract fun bindCommonsNetworkWrapper(
        default: CommonsNetworkWrapperImpl
    ): CommonsNetworkWrapper

    @Module
    companion object {

        @Singleton
        @JvmStatic
        @Provides
        fun providesMoshiInstance(
            @CommonsMoshiAdapters adapters: Set<@JvmSuppressWildcards JsonAdapter.Factory>
        ): Moshi {
            return buildMoshi {
                adapters.forEach { adapter -> add(adapter) }
            }
        }

        @Singleton
        @Provides
        @JvmStatic
        fun providesRetrofit(
            networkWrapper: CommonsNetworkWrapper
        ): Retrofit {
            return networkWrapper.buildRetrofit()
        }
    }
}
