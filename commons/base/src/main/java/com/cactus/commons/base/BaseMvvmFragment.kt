/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseMvvmFragment : Fragment(), HasAndroidInjector {

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        (context.applicationContext as HasAndroidInjector)
            .androidInjector()
            .inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    @Inject
    lateinit var factory: DaggerViewModelFactory

    /**
     * Create a Activity View Model, a shared view model between Activity and Fragment
     */
    inline fun <reified VM : BaseViewModel> appActivityViewModel(): ActivityVMFragmentDelegate<VM> =
        ActivityVMFragmentDelegate(VM::class) {
            this.requireBaseActivity().vmFactory
        }

    /**
     * Create a Fragment own View Model
     */
    inline fun <reified VM : BaseViewModel> appViewModel(): FragmentViewModelDelegate<VM> =
        FragmentViewModelDelegate(VM::class, this as Fragment) { factory }

    fun requireBaseActivity() = ((this as Fragment).requireActivity() as BaseMvvmActivity)
}
