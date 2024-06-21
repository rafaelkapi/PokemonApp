package com.cactus.pokemon.di

import com.cactus.pokemon.PokemonActivity
import com.cactus.pokemon.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMovieActivity(): PokemonActivity

}
