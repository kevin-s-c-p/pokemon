package mx.com.myapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.myapplication.repositories.local.pokemon.LocalPokemonRepository
import mx.com.myapplication.repositories.local.pokemon.LocalPokemonRepositoryImp
import mx.com.myapplication.repositories.pokemon.PokemonRepository
import mx.com.myapplication.repositories.pokemon.PokemonRepositoryImp
import mx.com.myapplication.repositories.user.UserRepository
import mx.com.myapplication.repositories.user.UserRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun userRepository(userRepositoryImp: UserRepositoryImp): UserRepository

    @Binds
    abstract fun pokemonRepository(pokemonRepositoryImp: PokemonRepositoryImp): PokemonRepository

    @Binds
    abstract fun localPokemonRepository(localPokemonRepositoryImp: LocalPokemonRepositoryImp): LocalPokemonRepository
}