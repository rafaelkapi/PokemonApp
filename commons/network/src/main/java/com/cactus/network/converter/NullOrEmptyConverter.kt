/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.network.converter

import java.lang.reflect.Type
import javax.inject.Inject
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

class NullOrEmptyConverter @Inject constructor() : Converter.Factory() {

    fun converterFactory() = this

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = object : Converter<ResponseBody, Any?> {
        val nextResponseBodyConverter =
            retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

        override fun convert(value: ResponseBody) =
            if (value.contentLength() != EMPTY) nextResponseBodyConverter.convert(value) else null
    }

    companion object {
        private const val EMPTY = 0L
    }
}
