package com.intelligent.pokemon.ui.screens.detail

import DetailContentSuccess

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.intelligent.pokemon.data.remote.responses.Pokemon
import com.intelligent.pokemon.model.RequestState
import com.intelligent.pokemon.ui.components.PokemonTopAppBar
import com.intelligent.pokemon.ui.screens.common.ContentLoading
import com.intelligent.pokemon.ui.viewmodel.PokemonViewModel
import java.util.*
import com.intelligent.pokemon.R
import com.intelligent.pokemon.ui.screens.common.ContentError

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    pokemonName: String,
    viewModel: PokemonViewModel,
    onBackClicked: () -> Unit
) {
    val pokemonInfo = viewModel.pokemonInfo.collectAsState()

    Scaffold(topBar = {
        PokemonTopAppBar(
            title = "$pokemonName Details",
            navIcon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onBackClicked.invoke() },
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            },
            hasNavIcon = true
        )
    })
    {
        Column(modifier = Modifier.fillMaxSize()) {
            PokemonDetail(
                pokemonInfo = pokemonInfo.value,
                onRetryClicked = { viewModel.getPokemonInfo(pokemonName) })
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonInfo(pokemonName)
    }
}


@Composable
fun PokemonDetail(
    pokemonInfo: RequestState<Pokemon>,
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit
) {
    when (pokemonInfo) {
        is RequestState.Success -> {
            DetailContentSuccess(
                pokemonInfo = pokemonInfo.data,
                modifier = modifier
                    .offset(y = (-20).dp)
            )
        }
        is RequestState.Error -> {
            ContentError(
                message = pokemonInfo.error.message ?: "Error loading Pokemon Detail",
                onRetryClicked = onRetryClicked
            )
        }
        is RequestState.Loading -> {
            ContentLoading(
                message = stringResource(id = R.string.loading_detail)
            )
        }
        else -> {}
    }
}


