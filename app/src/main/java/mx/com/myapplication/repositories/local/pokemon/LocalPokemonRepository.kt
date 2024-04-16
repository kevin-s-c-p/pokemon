package mx.com.myapplication.repositories.local.pokemon

import kotlinx.coroutines.flow.Flow
import mx.com.domain.items.PokemonItem
import mx.com.web.NetworkResult

interface LocalPokemonRepository {
    suspend fun upsertPokemon(pokemon: PokemonItem): Boolean
    suspend fun getMyPokemons(): List<PokemonItem>
}