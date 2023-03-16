package com.intelligent.pokemon.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.intelligent.pokemon.R
import com.intelligent.pokemon.model.Pokemon
import com.intelligent.pokemon.model.RequestState
import com.intelligent.pokemon.ui.components.PokemonTopAppBar
import com.intelligent.pokemon.ui.screens.common.ContentError
import com.intelligent.pokemon.ui.screens.common.ContentLoading
import com.intelligent.pokemon.ui.viewmodel.PokemonViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    viewModel: PokemonViewModel,
    onPokemonClick: (String) -> Unit
) {

    val requestState by viewModel.pokemonList.collectAsState()

    Scaffold(
        topBar = { PokemonTopAppBar(hasNavIcon = false) },
        content = {
            ListContent(
                requestState = requestState,
                viewModel = viewModel,
                onPokemonClick = onPokemonClick
            )
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonList()
    }

}

@Composable
fun ListContent(
    requestState: RequestState<List<Pokemon>>,
    viewModel: PokemonViewModel,
    onPokemonClick: (String) -> Unit
) {
    when (requestState) {
        is RequestState.Success<List<Pokemon>> -> {
            var pokemonList = requestState.data
            val term by viewModel.term.observeAsState("")
            ListContentSuccess(
                pokemonList = pokemonList,
                term = term,
                viewModel = viewModel,
                onPokemonClick = onPokemonClick
            )
        }
        is RequestState.Loading -> {
            ContentLoading(
                message = stringResource(id = R.string.loading_list)
            )
        }
        is RequestState.Error -> {
            val errorMessage =
                (requestState as RequestState.Error).error.message ?: "Error Loading Pokemon"
            ContentError(
                message = errorMessage,
                onRetryClicked = { viewModel.getPokemonList() }
            )
        }
        else -> {
            ContentLoading(
                message = stringResource(id = R.string.loading_list)
            )
        }
    }
}