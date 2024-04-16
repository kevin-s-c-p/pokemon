package mx.com.domain.models

import com.google.gson.annotations.SerializedName
import mx.com.domain.items.PokemonItem

data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val images: SpritesResponse
) {
    fun toPokemonItem(): PokemonItem {
        return PokemonItem(
            id = this.id.toString(),
            name = this.name,
            image = this.images.imageFront
        )
    }
}
