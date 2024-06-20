/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun <VB : ViewBinding> ViewGroup.viewBinding(
    attachToRoot: Boolean,
    binding: (LayoutInflater, ViewGroup, Boolean) -> VB
) = lazy(LazyThreadSafetyMode.NONE) {
    binding(LayoutInflater.from(context), this, attachToRoot)
}

fun <VB : ViewBinding> ViewGroup.bindViewBinding(
    attachToRoot: Boolean,
    binding: (LayoutInflater, ViewGroup, Boolean) -> VB
): VB {
    return binding(LayoutInflater.from(context), this, attachToRoot)
}
