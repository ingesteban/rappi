package dev.esteban.rappi.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Black20 = Color(0x33000000)
val DarkRed1 = Color(0x33800606)
val DarkRed2 = Color(0x80800606)
val Yellow = Color(0xFFF6C700)
val Red = Color(0xFFDD6969)

data class RappiColors(
    val darkRed1: Color = DarkRed1,
    val darkRed2: Color = DarkRed2,
    val black20: Color = Black20,
    val yellow: Color = Yellow,
    val red: Color = Red,
)

val LocalRappiColors = staticCompositionLocalOf { RappiColors() }
