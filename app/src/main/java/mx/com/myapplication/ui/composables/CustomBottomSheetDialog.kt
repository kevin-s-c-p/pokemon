package mx.com.myapplication.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mx.com.domain.items.PokemonItem

@Composable
fun BottomSheetPokemonInformation(
    isVisible: Boolean,
    pokemon: PokemonItem?,
    closeModal: () -> Unit
) {
    ModalBottomSheet(
        isVisible = isVisible,
        closeModal = closeModal
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomImage(
                image = pokemon?.image ?: "",
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(.35f).fillMaxHeight(.15f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.fillMaxHeight(.02f))

            Text(
                text = pokemon?.name ?: "",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}