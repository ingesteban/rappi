package dev.esteban.rappi.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class RappiShape(

    val card: RoundedCornerShape = RoundedCornerShape(4.dp),

    val small: RoundedCornerShape = RoundedCornerShape(8.dp),

    val medium: RoundedCornerShape = RoundedCornerShape(10.dp),

    val large: RoundedCornerShape = RoundedCornerShape(12.dp),

    val extraLarge: RoundedCornerShape = RoundedCornerShape(20.dp)

)

val LocalRappiShape = staticCompositionLocalOf { RappiShape() }