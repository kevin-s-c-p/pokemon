package mx.com.myapplication.ui.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheet(
    isVisible: Boolean,
    closeModal: () -> Unit,
    content: @Composable () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    if (isVisible) {
        androidx.compose.material3.ModalBottomSheet(
            sheetState = modalBottomSheetState,
            onDismissRequest = { closeModal() }
        ) {
            content()
        }
    }
}