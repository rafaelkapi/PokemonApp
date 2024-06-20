package com.cactus.pokedex.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.cactus.commons.base.BaseMvvmFragment

class PokedexFragment : BaseMvvmFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View =
        ComposeView(requireBaseActivity()).apply {
        setContent { }
    }

}
