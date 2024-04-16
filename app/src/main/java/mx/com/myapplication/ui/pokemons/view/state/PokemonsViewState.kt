package mx.com.myapplication.ui.pokemons.view.state

import mx.com.domain.items.PokemonItem
import mx.com.myapplication.viewstate.ViewState

data class PokemonsViewState(
    val myPokemons: List<PokemonItem> = emptyList(),
    val showModalDelete: Boolean = false,
    val pokemonSelected: PokemonItem? = null,
    val isLoading: Boolean = false
): ViewState()
