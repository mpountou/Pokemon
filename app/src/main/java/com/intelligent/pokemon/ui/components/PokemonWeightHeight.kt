package com.intelligent.pokemon.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PokemonWeightHeight(
    weight: String,
    height: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$weight kg",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(id = com.intelligent.pokemon.R.string.weight),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = MaterialTheme.typography.subtitle1.fontWeight
                )
            )

        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$height m",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(id = com.intelligent.pokemon.R.string.height),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = MaterialTheme.typography.subtitle1.fontWeight
                )
            )
        }
    }
}


@Preview
@Composable
fun PokemonWeightHeightPrev() {
    PokemonWeightHeight(weight = "2 kg", height = "2.03 m")
}