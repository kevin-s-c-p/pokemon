package mx.com.myapplication.repositories.local.pokemon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.com.database.DBPokemonApp
import mx.com.domain.items.PokemonItem
import mx.com.myapplication.repositories.BaseRepository
import mx.com.web.NetworkResult
import javax.inject.Inject

class LocalPokemonRepositoryImp @Inject constructor(
    private val dbPokemonApp: DBPokemonApp
): LocalPokemonRepository, BaseRepository() {
    override suspend fun upsertPokemon(pokemon: PokemonItem): Boolean {
        return if (dbPokemonApp.PokemonDao().getPokemon().size < 10) {
            dbPokemonApp.PokemonDao().upsert(pokemon.toEntity())
            true
        } else
            false
    }

    override suspend fun getMyPokemons(): List<PokemonItem> {
        return dbPokemonApp.PokemonDao().getPokemon().map {
            PokemonItem(it.id.toString(), it.image, it.name)
        }
    }

}