package com.intelligent.pokemon.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.intelligent.pokemon.R

@Composable
fun PokemonTopAppBar(
    title: String = stringResource(id = R.string.app_name),
    hasNavIcon: Boolean = false,
    navIcon: @Composable () -> Unit = { }
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = if (hasNavIcon) navIcon else null

    )
}