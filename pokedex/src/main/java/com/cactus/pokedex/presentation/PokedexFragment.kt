package com.cactus.pokedex.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.GridLayoutManager
import com.cactus.commons.base.BaseMvvmFragment
import com.cactus.commons.viewbinding.viewBinding
import com.cactus.movie.R
import com.cactus.movie.databinding.FragmentLayoutBinding
import com.cactus.pokedex.presentation.adapter.PokemonAdapter
import com.cactus.pokedex.presentation.model.PokemonDetailVo
import com.cactus.pokedex.presentation.model.PokemonSpecVo
import com.cactus.pokedex.presentation.model.PokemonStatsVo
import com.cactus.pokedex.presentation.model.PokemonType
import com.cactus.pokedex.presentation.model.PokemonVo
import com.cactus.pokedex.presentation.view.ContainerPokedex
import com.cactus.pokedex.presentation.view.PokemonDetail

class PokedexFragment : BaseMvvmFragment() {

    private val pokedexBinding by viewBinding(FragmentLayoutBinding::inflate)

    private val pokemonAdapter: PokemonAdapter by lazy { PokemonAdapter{} }


    var listPokemons =  listOf(
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        PokemonVo(
            "#0025",
            "Pikachu",
            listOf(
                PokemonType(
                    "Electric",
                    R.drawable.ic_grass,
                    324
                ),
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        ),
        PokemonVo(
            "#0001",
            "Bulbasaur",
            listOf(
                PokemonType(
                    "Grass",
                    R.drawable.ic_grass,
                    324
                ),
                PokemonType(
                    "Poisson",
                    R.drawable.ic_grass,
                    324
                )
            ),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
    )

    val stats = PokemonStatsVo(
        hp = "HP" to 0.3f,
        attack = "Attack" to 0.55f,
        defense = "Defense" to 0.7f,
        specialAttack = 0.7f,
        specialDefense = 0.8f,
        speed = "Speed" to 0.5f
    )

    val spec = PokemonSpecVo(
        height = "0.4m",
        weight = "6.0kg",
        category = "Seed",
        type = listOf(
            R.drawable.pokemon_type_icon_electric to "Eletric",
            R.drawable.pokemon_type_icon_bug to "Bug",
            R.drawable.pokemon_type_icon_grass to "Grass",
        ),
        weaknesses = listOf(
            R.drawable.pokemon_type_icon_electric to "Eletric",
            R.drawable.pokemon_type_icon_bug to "Bug",
            R.drawable.pokemon_type_icon_grass to "Grass",
            R.drawable.pokemon_type_icon_bug to "Bug",
            R.drawable.pokemon_type_icon_grass to "Grass",
        )
    )

    val detailVo = PokemonDetailVo(
        "0025",
        "Pikachu",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/25.png",
        R.drawable.backgroud_detail_1,
        listOf(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/172.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/26.png",
        ),
        spec,
        stats,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
            setContent {
                val listPokemonsState = remember { mutableStateOf(listPokemons) }
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PokemonDetail(detailVo)
                    ContainerPokedex(pokedexBinding, listPokemonsState)
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        pullToRefresh()
        pokemonAdapter.setViewItems(listPokemons)
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Funfando",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }

    private fun setupObservers() {
//        viewModel.state.observe(viewLifecycleOwner, SafeObserver { vo -> handlerState(vo) })
//
//        viewModel.similarMovies.observe(
//            viewLifecycleOwner,
//            SafeObserver { vo -> populateViewSimilarMovies(vo) })
    }

    private fun setupRecyclerView() {
        pokedexBinding.content.pokemonList.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = pokemonAdapter
        }
    }

    private fun pullToRefresh() {
        pokedexBinding.content.swipeRefreshLayout.apply {
            setOnRefreshListener {
//                viewModel.refresh()
                isRefreshing = false

            }
        }
    }

}
