package mx.com.myapplication.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ModalSheet(
    isVisible: Boolean,
    closeModal: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = { closeModal() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(.8f),
                shape = RoundedCornerShape(10.dp)
            ) { content() }
        }
    }
}