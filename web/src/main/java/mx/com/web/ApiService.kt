package mx.com.web

import mx.com.domain.models.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}/")
    suspend fun requestGetPokemon(
        @Path("id") idPokemon: String
    ): Response<PokemonResponse>
}