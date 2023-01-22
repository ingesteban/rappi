package dev.esteban.rappi.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush

val GradientSplash = Brush.verticalGradient(
    listOf(
        Black20,
        DarkRed1,
    )
)

data class RappiGradient(
    val gradientSplash: Brush = GradientSplash
)

val LocalRappiGradient = staticCompositionLocalOf { RappiGradient() }
