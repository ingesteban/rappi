package dev.esteban.rappi.utils

import androidx.compose.ui.Modifier

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition)
        then(modifier(this))
    else this
}