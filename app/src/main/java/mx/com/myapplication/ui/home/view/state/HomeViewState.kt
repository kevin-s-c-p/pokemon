package mx.com.myapplication.ui.home.view.state

import mx.com.domain.items.PokemonItem
import mx.com.myapplication.viewstate.ViewState

data class HomeViewState(
    val pokemonFind: PokemonItem? = null,
    val startAnimationPokemonFind: Boolean = false,
    val whoIsThisPokemon: PokemonItem? = PokemonItem("", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png", "Shiny"),
    val isLoading: Boolean = false,
    val pokemonWrite: String = "",
    val isVisibleBottomSheetPokemon: Boolean = false,
    val isVisibleModalSaved: Boolean = false
): ViewState()
