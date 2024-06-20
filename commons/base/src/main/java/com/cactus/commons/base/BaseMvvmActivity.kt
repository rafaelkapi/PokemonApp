/*
 * Copyright (c)
 * 2018-2021 XP Inc
 * All Rights Reserved
 */
package com.cactus.commons.base

import androidx.fragment.app.FragmentActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseMvvmActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var vmFactory: DaggerViewModelFactory

    inline fun <reified VM : BaseViewModel> appViewModel(): ViewModelDelegate<VM> {
        return ViewModelDelegate(VM::class, this as FragmentActivity) { this.vmFactory }
    }
}
