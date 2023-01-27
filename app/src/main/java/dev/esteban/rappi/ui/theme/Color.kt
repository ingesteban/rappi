package dev.esteban.rappi.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Black20 = Color(0x33000000)
val DarkRed1 = Color(0x33800606)
val DarkRed2 = Color(0x80800606)
val Yellow = Color(0xFFF6C700)
val Red = Color(0xFFDD6969)
val Gray1 = Color(0xFF8E8E8E)
val WhiteTransparent1 = Color(0xCCFFFFFF)
val WhiteTransparent2 = Color(0x66FFFFFF)

data class RappiColors(
    val darkRed1: Color = DarkRed1,
    val darkRed2: Color = DarkRed2,
    val black20: Color = Black20,
    val yellow: Color = Yellow,
    val red: Color = Red,
    val gray1: Color = Gray1,
    val whiteTransparent1: Color = WhiteTransparent1,
    val whiteTransparent2: Color = WhiteTransparent2,
)

val LocalRappiColors = staticCompositionLocalOf { RappiColors() }
