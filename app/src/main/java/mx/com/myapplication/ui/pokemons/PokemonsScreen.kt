package mx.com.myapplication.ui.pokemons

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.com.domain.items.PokemonItem
import mx.com.myapplication.R
import mx.com.myapplication.ui.composables.CustomImage
import mx.com.myapplication.ui.composables.CustomMainMenu
import mx.com.myapplication.ui.pokemons.view.event.PokemonsViewEvent
import mx.com.myapplication.ui.pokemons.view.state.PokemonsViewState

@Composable
fun PokemonsScreen(
    navController: NavController,
    viewModel: PokemonsViewModel = hiltViewModel()
) {
    CustomMainMenu(
        navController = navController,
        titleMenu = R.string.my_pokemons
    ) {

        val state by viewModel.getState<PokemonsViewState>().collectAsState()

        ModalDelete(
            isVisible = state.showModalDelete,
            pokemon = state.pokemonSelected,
            closeModal = { viewModel.onEvent(PokemonsViewEvent.HideModalDelete) },
            deletePokemon = { viewModel.onEvent(PokemonsViewEvent.DeletePokemon(it)) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.myPokemons) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    clickPokemon = {  },
                    showName = { viewModel.onEvent(PokemonsViewEvent.ShowNamePokemon(it)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PokemonCard(
    pokemon: PokemonItem,
    clickPokemon: (PokemonItem) -> Unit,
    showName: (name: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .combinedClickable(
                onClick = { clickPokemon(pokemon) },
                onLongClick = { showName(pokemon.name) },
            ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomImage(
                image = pokemon.image,
                modifier = Modifier
                    .fillMaxWidth(.2f)
                    .fillMaxHeight(.3f)
                    .clip(CircleShape)
            )

            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ModalDelete(
    isVisible: Boolean,
    pokemon: PokemonItem?,
    closeModal: () -> Unit,
    deletePokemon: (idPokemon: String) -> Unit
) {

}