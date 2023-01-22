package dev.esteban.rappi

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.esteban.rappi.navigation.ScreenNavigation
import dev.esteban.rappi.ui.theme.RappiTheme
import dev.esteban.rappi.views.movies.MoviesNavigation

@Composable
fun RappiApp() {
    val navController = rememberNavController()
    Scaffold(backgroundColor = RappiTheme.colors.black20) { innerPadding ->
        NavHost(
            navController = navController, startDestination = MoviesNavigation.route,
            Modifier.padding(innerPadding)
        ) {
            ScreenNavigation.allScreens.forEach { screen ->
                composable(
                    screen.route,
                    screen.arguments,
                ) { navBackStackEntry ->
                    screen.Content(navController, navBackStackEntry)
                }
            }
        }
    }
}
