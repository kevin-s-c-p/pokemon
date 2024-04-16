package mx.com.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mx.com.database.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(pokemon: PokemonEntity): Long

    @Query("SELECT * FROM Pokemon")
    suspend fun getPokemon(): List<PokemonEntity>

    @Query("SELECT COUNT(*) FROM Pokemon")
    suspend fun getSize(): Int

    @Delete
    suspend fun delete(pokemon: PokemonEntity): Int
}