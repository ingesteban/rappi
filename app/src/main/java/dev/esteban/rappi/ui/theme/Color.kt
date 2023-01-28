package dev.esteban.rappi.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Black20 = Color(0x33000000)
val DarkRed1 = Color(0x33800606)
val DarkRed2 = Color(0x80800606)
val Yellow = Color(0xFFF6C700)
val Red = Color(0xFFDD6969)
val Gray1 = Color(0xFF8E8E8E)
val Gray2 = Color(0xFF6C6C8F)
val Gray3 = Color(0xFF404161)
val WhiteTransparent1 = Color(0xCCFFFFFF)
val WhiteTransparent2 = Color(0x66FFFFFF)
val BlackTransparent1 = Color(0xB3000000)

data class RappiColors(
    val darkRed1: Color = DarkRed1,
    val darkRed2: Color = DarkRed2,
    val black20: Color = Black20,
    val yellow: Color = Yellow,
    val red: Color = Red,
    val gray1: Color = Gray1,
    val gray2: Color = Gray2,
    val gray3: Color = Gray3,
    val whiteTransparent1: Color = WhiteTransparent1,
    val whiteTransparent2: Color = WhiteTransparent2,
    val blackTransparent1: Color = BlackTransparent1,
)

val LocalRappiColors = staticCompositionLocalOf { RappiColors() }
