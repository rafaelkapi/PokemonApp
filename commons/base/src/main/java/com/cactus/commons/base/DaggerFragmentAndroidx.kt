package com.cactus.commons.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.internal.Beta
import javax.inject.Inject

@Beta
abstract class DaggerFragmentAndroidx : Fragment(), HasAndroidInjector {
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
}