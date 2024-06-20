/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class FragmentViewModelDelegate<T : BaseViewModel>(
    private val clazz: KClass<T>,
    private val fragment: Fragment,
    private val vmFactory: () -> DaggerViewModelFactory
) : ReadWriteProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return ViewModelProvider(fragment, vmFactory.invoke()).get(clazz.java)
            .apply { thisRef.lifecycle.addObserver(this) }
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {}
}
