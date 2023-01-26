package dev.esteban.rappi.composecommon

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.esteban.rappi.ui.theme.RappiTheme

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = RappiTheme.typography.h1,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}