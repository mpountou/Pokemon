package com.intelligent.pokemon.ui.screens.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.intelligent.pokemon.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentError(
    message: String = "Error loading Pokemon",
    onRetryClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = message,
            style = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = MaterialTheme.typography.subtitle1.fontWeight
            )
        )
        Button(onClick = onRetryClicked) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}