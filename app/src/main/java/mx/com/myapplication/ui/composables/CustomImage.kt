package mx.com.myapplication.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import mx.com.myapplication.R

@Composable
fun CustomImage(
    image: Any,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Crop,
    tint: Color? = null,
    @DrawableRes imageError: Int = R.drawable.ic_pokeball_color
) {
    AsyncImage(
        model = image,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = if (tint != null) ColorFilter.tint(tint) else null,
        error = painterResource(id = imageError)
    )
}