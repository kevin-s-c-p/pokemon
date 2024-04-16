package mx.com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import mx.com.database.entities.PokemonEntity
import mx.com.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userEntity: UserEntity)

    @Query("SELECT * FROM User LIMIT 1")
    fun getUser(): UserEntity

    @Update
    suspend fun update(userEntity: UserEntity)
}