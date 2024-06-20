/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.network

import com.cactus.network.qualifiers.CommonsIoScheduler
import com.cactus.network.qualifiers.CommonsMainScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class CommonsSchedulersModule {

    @Singleton
    @Provides
    @CommonsIoScheduler
    fun provideIOScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @CommonsMainScheduler
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
