package com.cactus.pokemon.di

import android.app.Application
import com.cactus.pokemon.CustomApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainActivityBuilderModule::class,
    ])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: CustomApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

