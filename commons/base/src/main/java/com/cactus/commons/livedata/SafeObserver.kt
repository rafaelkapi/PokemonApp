/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.livedata

import androidx.lifecycle.Observer

/**
 * An alternative for the default Observer used for listening changes on LiveData fields.
 * It already handles the case in which the field is null.
 *
 * Example:
 * liveField.observe(this, SafeObserver {
 *      doSomething(it)
 * })
 *
 */
class SafeObserver<T>(private val callback: (T) -> Unit) : Observer<T> {
    override fun onChanged(value: T) {
        value?.let {
            callback(it)
        }
    }
}
