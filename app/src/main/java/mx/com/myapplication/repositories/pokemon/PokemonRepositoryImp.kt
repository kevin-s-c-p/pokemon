package mx.com.myapplication.repositories.pokemon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.com.database.DBPokemonApp
import mx.com.database.dao.PokemonDao
import mx.com.database.entities.PokemonEntity
import mx.com.domain.items.PokemonItem
import mx.com.domain.models.PokemonResponse
import mx.com.myapplication.repositories.ApiResponse
import mx.com.myapplication.repositories.BaseRepository
import mx.com.web.ApiService
import javax.inject.Inject
import kotlin.random.Random

class PokemonRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val dbPokemon: DBPokemonApp
): BaseRepository(), PokemonRepository {
    override fun getPokemonDetail(idPokemon: Int): ApiResponse<Any> {
        TODO("Not yet implemented")
    }

    override fun requestFindNewPokemon(): ApiResponse<PokemonResponse> = safeApiCall {
        val idRandom = Random.nextInt(300)
        apiService.requestGetPokemon(idRandom.toString())
    }
}