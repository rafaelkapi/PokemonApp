package com.cactus.pokedex.di

import androidx.lifecycle.ViewModel
import com.cactus.commons.base.ViewModelKey
import com.cactus.movie.moviedetails.di.qualifiers.FragmentScope
import com.cactus.network.di.CommonsNetworkBuilderModule
import com.cactus.pokedex.presentation.PokedexFragment
import com.cactus.pokedex.presentation.PokedexViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [PokedexModule::class,])
abstract class PokedexModuleBuilder {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindsDetailsFragment(): PokedexFragment
}

@Module(includes = [CommonsNetworkBuilderModule::class])
abstract class PokedexModule {

//    @Binds
//    abstract fun bindDetailsRepository(impl: DetailsRepositoryImpl): DetailsRepository
//
//    @Binds
//    abstract fun bindDetailsInteractor(impl: DetailsInteractorImpl): DetailsInteractor
//
    @Binds
    @IntoMap
    @ViewModelKey(PokedexViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: PokedexViewModel): ViewModel

//    @Module
//    companion object {
//        @JvmStatic
//        @Provides
//        fun provideService(retrofit: Retrofit): DetailsService =
//            retrofit.create(DetailsService::class.java)
//    }
}




