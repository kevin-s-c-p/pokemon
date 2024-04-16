package mx.com.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.com.myapplication.ui.home.HomeScreen
import mx.com.myapplication.ui.pokemons.PokemonsScreen
import mx.com.myapplication.ui.splash.SplashScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.SPLASH,
    ) {
        composable(Screens.SPLASH) {
            SplashScreen(navController)
        }

        composable(Screens.HOME) {
            HomeScreen(navController)
        }

        composable(Screens.POKEMONS) {
            PokemonsScreen(navController)
        }

        composable(Screens.PROFILE) {}
    }
}