package com.intelligent.pokemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.intelligent.pokemon.model.Screen
import com.intelligent.pokemon.ui.screens.detail.DetailScreen
import com.intelligent.pokemon.ui.screens.list.ListScreen
import com.intelligent.pokemon.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonNavGraph(
    navController: NavHostController,
    viewModel: PokemonViewModel
) {
    NavHost(navController = navController, startDestination = Screen.LIST.name) {
        listScreen(
            navController = navController,
            viewModel = viewModel
        )
        detailScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}


fun NavGraphBuilder.listScreen(
    navController: NavHostController,
    viewModel: PokemonViewModel
) {
    composable(route = Screen.LIST.name) {
        ListScreen(
            viewModel = viewModel,
            onPokemonClick = { navController.navigate(Screen.DETAIL.name + "/$it") }
        )
    }
}

fun NavGraphBuilder.detailScreen(
    navController: NavHostController,
    viewModel: PokemonViewModel
){
    composable(
        route = Screen.DETAIL.name + "/{pokemonName}",
        arguments = listOf(
            navArgument("pokemonName") { type = NavType.StringType }
        )
    ) {

        val pokemonName = it.arguments?.getString("pokemonName") ?: ""

        DetailScreen(
            pokemonName = pokemonName,
            viewModel = viewModel,
            onBackClicked = { navController.popBackStack() }
        )

    }
}