package mx.com.myapplication.ui.home.view.event

sealed class HomeViewEvent {
    data class WritePokemon(val pokemon: String): HomeViewEvent()
    object IsThisPokemon: HomeViewEvent()
    object PokemonFindAnimationEnd: HomeViewEvent()
    object SavePokemon: HomeViewEvent()
    object HideBottomSheetDialog: HomeViewEvent()
    object ShowBottomSheetDialog: HomeViewEvent()
    object SearchNewPokemon: HomeViewEvent()
    object ShowNamePokemon: HomeViewEvent()
    object ShowModal: HomeViewEvent()
    object HideModal: HomeViewEvent()
}
