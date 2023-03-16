package com.intelligent.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.intelligent.pokemon.navigation.PokemonNavGraph
import com.intelligent.pokemon.ui.theme.PokemonTheme
import com.intelligent.pokemon.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                val navController = rememberNavController()
                val viewModel: PokemonViewModel = viewModel()
                PokemonNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}