package mx.com.domain.items

import mx.com.database.entities.PokemonEntity

data class PokemonItem(
    val id: String,
    val image: String,
    val name: String
) {
    fun toEntity(): PokemonEntity = PokemonEntity(
        this.id.toInt(),
        this.name,
        this.image
    )
}
