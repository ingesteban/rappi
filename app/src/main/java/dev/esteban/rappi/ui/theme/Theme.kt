package dev.esteban.rappi.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color


@Composable
fun RappiTheme(
    typography: RappiTypography = RappiTheme.typography,
    colors: RappiColors = RappiTheme.colors,
    shapes: RappiShape = RappiTheme.shapes,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRappiTypography provides typography,
        LocalRappiColors provides colors,
        LocalRappiShape provides shapes
    ) {
        MaterialTheme(
            content = content,
            colors = LightColors,
        )
    }
}

object RappiTheme {
    val typography: RappiTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalRappiTypography.current

    val colors: RappiColors
        @Composable
        @ReadOnlyComposable
        get() = LocalRappiColors.current

    val shapes: RappiShape
        @Composable
        @ReadOnlyComposable
        get() = LocalRappiShape.current

    val gradient: RappiGradient
        @Composable
        @ReadOnlyComposable
        get() = LocalRappiGradient.current
}

private val LightColors = lightColors(
    primary = Color.Black,
    primaryVariant = Black20,
    onPrimary = Color.White,
    secondary = DarkRed2,
    secondaryVariant = DarkRed1,
    onSecondary = Color.White,
    error = Red
)
