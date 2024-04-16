package mx.com.myapplication.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import mx.com.myapplication.R

@Composable
fun BaseTextField(
    value: String,
    modifier: Modifier,
    @StringRes hint: Int = R.string.hint_who_is_this_pokemon,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusDirection: FocusDirection = FocusDirection.Down,
    valueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = { valueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(10.dp),
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(focusDirection) }),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
        ) {
            if (value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = hint),
                    color = Color.Gray
                )
            }

            it()
        }
    }
}