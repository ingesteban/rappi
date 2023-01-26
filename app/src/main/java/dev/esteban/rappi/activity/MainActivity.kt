package dev.esteban.rappi.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.esteban.rappi.RappiApp
import dev.esteban.rappi.ui.theme.RappiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RappiTheme {
                CompositionLocalProvider {
                    RappiApp()
                }
            }
        }
    }
}
