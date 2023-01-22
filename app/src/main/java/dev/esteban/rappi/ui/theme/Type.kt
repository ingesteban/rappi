package dev.esteban.rappi.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import dev.esteban.rappi.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semi_bold, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black)
)

val h1Typography: TextStyle = TextStyle(
    fontFamily = PoppinsFontFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    color = Color.White
)

data class RappiTypography(
    val h1: TextStyle = h1Typography,
)

internal val LocalRappiTypography = staticCompositionLocalOf { RappiTypography() }
