package com.intelligent.pokemon.ui.screens.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentLoading(
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
        Text(
            text = message,
            style = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = MaterialTheme.typography.subtitle1.fontWeight
            )
        )
    }
}

