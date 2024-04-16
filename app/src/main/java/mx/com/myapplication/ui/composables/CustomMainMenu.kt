package mx.com.myapplication.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMainMenu(
    navController: NavController,
    @StringRes titleMenu: Int,
    showBottomMenu: Boolean = true,
    showReturnIcon: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomBottomBar(showBottomMenu) { route ->
                navController.navigate(route) { launchSingleTop = true }
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            CustomToolbar(
                title = titleMenu,
                showReturnIcon = showReturnIcon
            )
            content()
        }
    }
}