package com.cactus.commons.livedata

sealed class ViewState {
    data class Success<out T : Any>(val value: T) : ViewState()
    object Normal : ViewState()
    object Loading : ViewState()
    data class ErrorValue<out T>(val error: T? = null) : ViewState()
    data class Error(val throwable: Throwable? = null) : ViewState()

    val Success<*>.position: Int get() = 1
    val Normal.position: Int get() = 0
    val Loading.position: Int get() = 0
    val ErrorValue<*>.position: Int get() = 2
    val Error.position: Int get() = 2
}

