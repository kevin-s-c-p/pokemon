package mx.com.myapplication.ui.splash

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.com.myapplication.R
import mx.com.myapplication.navigation.Screens
import mx.com.myapplication.ui.splash.view.event.SplashViewEvent
import mx.com.myapplication.ui.splash.view.state.SplashViewState

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val state by viewModel.getState<SplashViewState>().collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(SplashViewEvent.VerifyUser)
    }

    LaunchedEffect(key1 = state.splashLoadedCompleted) {
        if (state.splashLoadedCompleted) {
            navController.popBackStack()
            navController.navigate(Screens.HOME)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.pokeball_icon),
            contentDescription = "",
            tint = if (darkTheme) Color.White else Color.Black,
            modifier = Modifier.fillMaxSize(.3f)
        )
    }
}