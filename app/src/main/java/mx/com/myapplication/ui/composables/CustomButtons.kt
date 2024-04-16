package mx.com.myapplication.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun DoubleButton(
    modifier: Modifier = Modifier,
    @StringRes textFirstButton: Int,
    @StringRes textSecondButton: Int,
    firstButtonColor: Color = MaterialTheme.colorScheme.tertiary,
    secondButtonColor: Color = MaterialTheme.colorScheme.onTertiary,
    shapeFirstButton: Shape = RoundedCornerShape(15.dp),
    shapeSecondButton: Shape = RoundedCornerShape(15.dp),
    clickFirstButton: () -> Unit,
    clickSecondButton: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            shape = shapeFirstButton,
            colors = ButtonDefaults.buttonColors(containerColor = firstButtonColor),
            onClick = { clickFirstButton() }
        ) {
            Text(
                text = stringResource(id = textFirstButton),
                style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        Button(
            modifier = Modifier.weight(1f),
            shape = shapeSecondButton,
            colors = ButtonDefaults.buttonColors(containerColor = secondButtonColor),
            onClick = { clickSecondButton() }
        ) {
            Text(
                text = stringResource(id = textSecondButton),
                style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
fun SimpleButton(
    modifier: Modifier = Modifier,
    @StringRes textButton: Int,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.background,
    shapeButton: Shape = RoundedCornerShape(15.dp),
    clickButton: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = shapeButton,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        onClick = { clickButton() }
    ) {
        Text(
            text = stringResource(id = textButton),
            style = MaterialTheme.typography.titleSmall.copy(color = textColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}