import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.intelligent.pokemon.data.remote.responses.Pokemon
import com.intelligent.pokemon.ui.components.PokemonTypeSection
import com.intelligent.pokemon.ui.components.PokemonWeightHeight
import java.util.*

@Composable
fun DetailContentSuccess(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonInfo.id}.png",
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "#${pokemonInfo.id} ${pokemonInfo.name.capitalize(Locale.ROOT)}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
        PokemonTypeSection(
            types = pokemonInfo.types
        )
        PokemonWeightHeight(
            weight = pokemonInfo.weight.toDouble().div(10).toString(),
            height = pokemonInfo.height.toDouble().div(10).toString()
        )
    }
}



