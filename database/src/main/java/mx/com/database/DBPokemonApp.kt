package mx.com.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.com.database.dao.PokemonDao
import mx.com.database.dao.UserDao
import mx.com.database.entities.PokemonEntity
import mx.com.database.entities.UserEntity

@Database(entities = [UserEntity::class, PokemonEntity::class], version = 1, exportSchema = false)
abstract class DBPokemonApp: RoomDatabase() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context): DBPokemonApp =
            Room.databaseBuilder(context, DBPokemonApp::class.java, "DBPokemon")
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun userDao(): UserDao

    abstract fun PokemonDao(): PokemonDao
}