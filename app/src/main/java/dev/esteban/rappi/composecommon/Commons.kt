package dev.esteban.rappi.composecommon

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun TextWithBox(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(28.dp)
            .padding(horizontal = 8.dp)
            .clip(RappiTheme.shapes.card)
            .background(RappiTheme.colors.whiteTransparent1)
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = text,
            style = RappiTheme.typography.h3.copy(color = Color.Black),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun TextWithIcon(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(28.dp)
            .padding(horizontal = 8.dp)
            .clip(RappiTheme.shapes.card)
            .background(RappiTheme.colors.yellow)
            .padding(horizontal = 4.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = RappiTheme.typography.h3.copy(color = Color.Black),
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth()
            .height(46.dp)
            .border(
                BorderStroke(1.dp, RappiTheme.colors.whiteTransparent2),
                RappiTheme.shapes.medium
            ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = RappiTheme.typography.h2
        )
    }
}
