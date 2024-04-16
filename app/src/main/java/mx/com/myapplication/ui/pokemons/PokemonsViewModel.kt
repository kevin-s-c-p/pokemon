package mx.com.myapplication.ui.pokemons

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.myapplication.repositories.local.pokemon.LocalPokemonRepository
import mx.com.myapplication.ui.pokemons.view.event.PokemonsViewEvent
import mx.com.myapplication.ui.pokemons.view.state.PokemonsViewState
import mx.com.myapplication.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonRepository: LocalPokemonRepository
): BaseViewModel() {
    init {
        initViewState(PokemonsViewState())
        requestMyPokemons()
    }

    fun onEvent(event: PokemonsViewEvent) {
        when(event) {
            is PokemonsViewEvent.DeletePokemon -> {  }
            is PokemonsViewEvent.ShowNamePokemon -> showToast(event.name)
            PokemonsViewEvent.HideModalDelete -> {  }
            PokemonsViewEvent.ShowModalDelete -> {  }
        }
    }

    private fun requestMyPokemons() {
        val state: PokemonsViewState = currentViewState()
        isLoading(true)

        viewModelScope.launch {
            val myPokemons = pokemonRepository.getMyPokemons()
            isLoading(false)
            updateViewState(state.copy(myPokemons = myPokemons))
        }
    }

    private fun isLoading(isLoading: Boolean) {
        val state: PokemonsViewState = currentViewState()
        updateViewState(state.copy(isLoading = isLoading))
    }
}