package com.intelligent.pokemon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.intelligent.pokemon.ui.viewmodel.PokemonViewModel

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel,
    onSearch: (String) -> Unit = {}
) {
    var text = viewModel.term.observeAsState("")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = null) },
            trailingIcon = {
                if (text.value.trim().isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable { viewModel.updateTerm("") },
                        imageVector = Icons.Rounded.Clear, contentDescription = null
                    )
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            value = text.value,
            onValueChange = {
                viewModel.updateTerm(it)
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
        )

    }
}