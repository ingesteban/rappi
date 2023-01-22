package dev.esteban.rappi.views.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.esteban.rappi.ui.theme.RappiTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(){
    Scaffold(topBar = {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(
                    text = "Movies",
                    style = RappiTheme.typography.h1,
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
        )
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Movies aaaa",
                style = RappiTheme.typography.h1,
            )
        }
    }
}