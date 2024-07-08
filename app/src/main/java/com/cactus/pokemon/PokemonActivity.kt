package com.cactus.pokemon

import android.os.Bundle
import com.cactus.commons.base.BaseMvvmActivity
import com.cactus.pokemon.databinding.PokemonActivityBinding

class PokemonActivity : BaseMvvmActivity() {

    private lateinit var binding: PokemonActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}