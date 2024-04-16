package mx.com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String
)
