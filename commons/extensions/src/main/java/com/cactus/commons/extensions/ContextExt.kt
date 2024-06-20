/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.extensions

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.isDebuggable(): Boolean {
    val pm = packageManager
    return try {
        val appInfo = pm.getApplicationInfo(packageName, 0)
        0 != (appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
    } catch (_: Exception) {
        false
    }
}

@ColorInt
fun Context.color(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

