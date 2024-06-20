/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.Locale
import javax.inject.Singleton

@Module
abstract class CommonsContextModule {

    @Binds
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAppResources(application: Application): Resources = application.resources

        @JvmStatic
        @Provides
        @Singleton
        fun provideLocalePtBr(): Locale = Locale("pt", "BR")
    }
}
