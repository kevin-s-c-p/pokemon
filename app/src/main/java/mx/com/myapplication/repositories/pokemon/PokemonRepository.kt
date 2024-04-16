package mx.com.myapplication.repositories.pokemon

import kotlinx.coroutines.flow.Flow
import mx.com.domain.items.PokemonItem
import mx.com.domain.models.PokemonResponse
import mx.com.myapplication.repositories.ApiResponse

interface PokemonRepository {
    fun getPokemonDetail(idPokemon: Int): ApiResponse<Any>
    fun requestFindNewPokemon(): ApiResponse<PokemonResponse>
}