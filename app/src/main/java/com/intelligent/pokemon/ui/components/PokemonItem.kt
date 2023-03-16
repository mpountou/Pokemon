package com.intelligent.pokemon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.intelligent.pokemon.model.Pokemon


@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onPokemonClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .padding(20.dp)
            .clickable { onPokemonClick.invoke(pokemon.pokemonName) },
        elevation = 5.dp,

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )
            Text(
                text = pokemon.pokemonName,
                style = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize)
            )
        }
    }
}