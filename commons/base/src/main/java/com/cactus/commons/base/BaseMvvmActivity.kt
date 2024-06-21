/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseMvvmActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as HasAndroidInjector)
            .androidInjector()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    @Inject
    lateinit var vmFactory: DaggerViewModelFactory

    inline fun <reified VM : BaseViewModel> appViewModel(): ViewModelDelegate<VM> {
        return ViewModelDelegate(VM::class, this as FragmentActivity) { this.vmFactory }
    }
}
