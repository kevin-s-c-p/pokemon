package mx.com.myapplication.ui.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mx.com.myapplication.repositories.local.pokemon.LocalPokemonRepository
import mx.com.myapplication.repositories.pokemon.PokemonRepository
import mx.com.myapplication.ui.home.view.event.HomeViewEvent
import mx.com.myapplication.ui.home.view.state.HomeViewState
import mx.com.myapplication.viewmodel.BaseViewModel
import mx.com.web.NetworkResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val localPokemonRepository: LocalPokemonRepository
): BaseViewModel() {
    init {
        initViewState(HomeViewState())
    }

    fun onEvent(event: HomeViewEvent) {
        when(event) {
            HomeViewEvent.HideBottomSheetDialog -> showBottomModal(false)
            HomeViewEvent.IsThisPokemon -> verifyPokemonFind()
            HomeViewEvent.PokemonFindAnimationEnd -> animationPokemon(false)
            HomeViewEvent.SavePokemon -> savePokemon()
            HomeViewEvent.SearchNewPokemon -> findNewPokemon()
            HomeViewEvent.ShowNamePokemon -> showNamePokemon()
            HomeViewEvent.ShowBottomSheetDialog -> showBottomModal(true)
            HomeViewEvent.HideModal -> showModal(false)
            HomeViewEvent.ShowModal -> showModal(true)
            is HomeViewEvent.WritePokemon -> writePokemon(event.pokemon)
        }
    }

    private fun showNamePokemon() {
        val state: HomeViewState = currentViewState()
        showToast(state.whoIsThisPokemon?.name ?: "")
    }

    private fun writePokemon(pokemon: String) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(pokemonWrite = pokemon))
    }

    private fun verifyPokemonFind() {
        val state: HomeViewState = currentViewState()

        viewModelScope.launch {
            if (state.pokemonWrite.lowercase() == state.whoIsThisPokemon?.name?.lowercase()) {
                updateViewState(
                    state.copy(
                        pokemonFind = state.whoIsThisPokemon,
                        pokemonWrite = ""
                    )
                )
                delay(100)
                animationPokemon(true)
                findNewPokemon()
            } else {
                showToast("Ups...")
            }
        }
    }

    private fun animationPokemon(isBeginning: Boolean) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(startAnimationPokemonFind = isBeginning))
    }

    private fun findNewPokemon() {
        val state: HomeViewState = currentViewState()

        pokemonRepository.requestFindNewPokemon().onEach {
            when(it) {
                is NetworkResult.Error -> {
                    showToast(it.error)
                }
                is NetworkResult.Exception -> { it.exception.printStackTrace() }
                is NetworkResult.Loading -> { isLoading(it.isLoading) }
                is NetworkResult.Success -> {
                    updateViewState(state.copy(whoIsThisPokemon = it.data?.toPokemonItem()))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun isLoading(isLoading: Boolean) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(isLoading = isLoading))
    }

    private fun showBottomModal(isVisible: Boolean) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(isVisibleBottomSheetPokemon = isVisible))
    }

    private fun showModal(isVisible: Boolean) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(isVisibleModalSaved = isVisible))
    }

    private fun savePokemon() {
        val state: HomeViewState = currentViewState()

        viewModelScope.launch {
            if (state.pokemonFind == null)
                return@launch

            val isSaved = localPokemonRepository.upsertPokemon(state.pokemonFind)

            if (isSaved) {
                updateViewState(state.copy(isVisibleModalSaved = true))
            } else {
                showToast("Ya no puedes guardar mas pokemones")
            }
        }
    }
}