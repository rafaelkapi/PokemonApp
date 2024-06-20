/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.viewbinding

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

/**
 * credits
 * https://zhuinden.medium.com/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c
 */
inline fun <VB : ViewBinding> Activity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> VB
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater(layoutInflater)
}
