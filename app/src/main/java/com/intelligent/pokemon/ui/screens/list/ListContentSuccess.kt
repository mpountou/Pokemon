package com.intelligent.pokemon.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.intelligent.pokemon.R
import com.intelligent.pokemon.model.Pokemon
import com.intelligent.pokemon.ui.components.PokemonItem
import com.intelligent.pokemon.ui.components.SearchBar
import com.intelligent.pokemon.ui.viewmodel.PokemonViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListContentSuccess(
    pokemonList: List<Pokemon>,
    term: String,
    viewModel: PokemonViewModel,
    onPokemonClick: (String) -> Unit
) {
    val filteredPokemonList = viewModel.filterPokemonByTerm(pokemonList,term)

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.pokemon_logo),
            contentDescription = null
        )

        SearchBar(
            onSearch = { viewModel.updateTerm(it) },
            viewModel = viewModel
        )

        val scrollState = rememberLazyGridState()
        LazyVerticalGrid(
            state = scrollState,
            columns = GridCells.Adaptive(minSize = 140.dp)
        ) {
            items(filteredPokemonList) { pokemon: Pokemon ->
                PokemonItem(
                    pokemon = pokemon,
                    onPokemonClick = onPokemonClick
                )
            }
        }
    }
}