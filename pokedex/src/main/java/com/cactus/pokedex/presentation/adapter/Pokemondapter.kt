package com.cactus.pokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cactus.movie.databinding.ItemPokemonBinding
import com.cactus.pokedex.presentation.model.PokemonVo
import com.squareup.picasso.Picasso

class PokemonAdapter : RecyclerView.Adapter<DetailsViewHolder>() {

    private val differ: AsyncListDiffer<PokemonVo?> = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setViewItems(list: List<PokemonVo>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        differ.currentList[position]?.let {
            holder.bind(it)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonVo>() {
            override fun areItemsTheSame(
                oldItem: PokemonVo,
                newItem: PokemonVo
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PokemonVo,
                newItem: PokemonVo
            ): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.posterUrl == newItem.posterUrl
            }
        }
    }
}

class DetailsViewHolder(private val view: ItemPokemonBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(movie: PokemonVo) {

        with(view) {
            Picasso.get().load(movie.posterUrl).into(view.poster)
            name.text = movie.name
            id.text = movie.id
            movie.types.forEachIndexed { index, pokemonType ->
                when (index) {
                    0 -> {
                        type1.visibility = android.view.View.VISIBLE
                        type1.text = pokemonType.type
                    }
                    1 -> {
                        type2.visibility = android.view.View.VISIBLE
                        type2.text = pokemonType.type
                    }
                }
            }
        }
    }
}