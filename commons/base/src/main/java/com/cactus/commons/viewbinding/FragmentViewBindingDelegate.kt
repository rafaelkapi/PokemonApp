/*
 * Copyright (c)
 * 2018-2022 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.cactus.commons.viewbinding.observer.DelegateLifecycleObserver
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * References:
 * https://developer.android.com/topic/libraries/view-binding#fragments
 * https://zhuinden.medium.com/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c
 * https://proandroiddev.com/make-android-view-binding-great-with-kotlin-b71dd9c87719
 */

abstract class LifecycleViewBindingDelegate<VB : ViewBinding> : ReadOnlyProperty<Fragment, VB> {

    protected open var binding: VB? = null
    protected var parentReference: ViewGroup? = null

    open val bindingObserver = object : DelegateLifecycleObserver() {
        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            parentReference = null
            binding = null
        }
    }

    protected fun setLifecycle(thisRef: Fragment) {
        thisRef.lifecycle.addObserver(bindingObserver)
    }

    protected fun isCalledBeforeInitialize(thisRef: Fragment): Boolean {
        val lifecycle = thisRef.lifecycle
        return !lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)
    }

    protected companion object {
        const val ACCESSES_AFTER_ON_DESTROY =
            "Should not attempt to get bindings when Fragment views are destroyed."
    }
}

class FragmentInflaterViewBindingDelegate<VB : ViewBinding>(
    private val viewBinder: (LayoutInflater) -> VB
) : LifecycleViewBindingDelegate<VB>() {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        binding?.let {
            return it
        }

        return if (isCalledBeforeInitialize(thisRef)) {
            println(ACCESSES_AFTER_ON_DESTROY)
            viewBinder(thisRef.layoutInflater)
        } else {
            setLifecycle(thisRef)
            val viewBinding = viewBinder(thisRef.layoutInflater)
            this.binding = viewBinding

            viewBinding
        }
    }
}

class FragmentBindViewBindingDelegate<VB : ViewBinding>(
    private val viewBinder: (View) -> VB
) : LifecycleViewBindingDelegate<VB>() {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (thisRef.view?.parent != parentReference) {
            parentReference = null
            binding = null
        }

        binding?.let {
            return it
        }

        return if (isCalledBeforeInitialize(thisRef)) {
            println(ACCESSES_AFTER_ON_DESTROY)
            parentReference = null
            viewBinder(thisRef.requireView())
        } else {
            parentReference = thisRef.view?.parent as? ViewGroup
            setLifecycle(thisRef)
            val viewBinding = viewBinder(thisRef.requireView())
            this.binding = viewBinding

            viewBinding
        }
    }
}

fun <VB : ViewBinding> viewBinding(
    viewBinder: (LayoutInflater) -> VB
) = FragmentInflaterViewBindingDelegate(viewBinder)

fun <VB : ViewBinding> viewBinding(
    viewBinder: (View) -> VB
) = FragmentBindViewBindingDelegate(viewBinder)
