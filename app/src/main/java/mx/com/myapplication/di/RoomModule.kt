package mx.com.myapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.com.database.DBPokemonApp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun provideRoomDB(
        @ApplicationContext
        context: Context
    ): DBPokemonApp = DBPokemonApp.newInstance(context)
}