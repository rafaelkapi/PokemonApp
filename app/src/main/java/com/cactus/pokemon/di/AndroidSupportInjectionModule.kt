package com.cactus.pokemon.di

import dagger.Module
import dagger.android.AndroidInjectionModule


@Module(includes = [AndroidInjectionModule::class])
abstract class AndroidSupportInjectionModule private constructor()
