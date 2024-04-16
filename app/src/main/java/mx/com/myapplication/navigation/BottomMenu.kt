package mx.com.myapplication.navigation

import androidx.annotation.DrawableRes
import mx.com.myapplication.R

sealed class BottomMenu (
    val route: String,
    @DrawableRes val icon: Int
) {
    object Home: BottomMenu(Screens.HOME, R.drawable.ic_home)
    object Profile: BottomMenu(Screens.PROFILE, R.drawable.ic_perfil)
    object Pokemon: BottomMenu(Screens.POKEMONS, R.drawable.ic_pokemon)
}
