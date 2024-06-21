package com.cactus.pokedex.presentation

import com.cactus.commons.base.BaseViewModel
import com.cactus.network.qualifiers.CommonsIoScheduler
import com.cactus.network.qualifiers.CommonsMainScheduler
import io.reactivex.Scheduler
import javax.inject.Inject


class PokedexViewModel @Inject constructor(
    @CommonsIoScheduler private val ioScheduler: Scheduler,
    @CommonsMainScheduler private val mainScheduler: Scheduler,
) : BaseViewModel() {

}