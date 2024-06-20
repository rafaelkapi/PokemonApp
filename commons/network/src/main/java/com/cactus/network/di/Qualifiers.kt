package com.cactus.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrivateKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor


