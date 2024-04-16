package mx.com.myapplication.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import mx.com.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    @StringRes title: Int,
    showReturnIcon: Boolean
) {
    CenterAlignedTopAppBar(
        title = { TitleToolbar(title = title) },
        navigationIcon = { NavigationIcon(showReturnIcon) },
        actions = {  },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
    )
}

@Composable
private fun TitleToolbar(
    @StringRes title: Int
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun NavigationIcon(showReturnIcon: Boolean) {
    if (showReturnIcon) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(.1f)
        )
    }
}