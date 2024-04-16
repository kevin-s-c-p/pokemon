package mx.com.myapplication.ui.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mx.com.myapplication.R
import mx.com.myapplication.navigation.BottomMenu

@Composable
fun CustomBottomBar(
    showBottomBar: Boolean,
    iconSelected: (route: String) -> Unit
) {
    val bottomMenu = listOf(BottomMenu.Home, BottomMenu.Pokemon, BottomMenu.Profile)

    if (showBottomBar) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.06f),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            bottomMenu.forEach {
                NavigationBarItem(
                    modifier = Modifier.padding(5.dp),
                    selected = false,
                    onClick = { iconSelected(it.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = stringResource(id = R.string.home),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxHeight(.7f)
                        )
                    }
                )
            }
        }
    }
}