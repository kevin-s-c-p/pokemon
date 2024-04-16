package mx.com.myapplication.ui.pokemons.view.event

sealed class PokemonsViewEvent {
    data class ShowNamePokemon(val name: String): PokemonsViewEvent()
    data class DeletePokemon(val idPokemon: String): PokemonsViewEvent()
    object ShowModalDelete: PokemonsViewEvent()
    object HideModalDelete: PokemonsViewEvent()
}
