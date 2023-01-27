package dev.esteban.rappi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dev.esteban.rappi.views.movie.MovieNavigation
import dev.esteban.rappi.views.movies.MoviesNavigation

interface ScreenNavigation {

    companion object {
        val allScreens = listOf(
            MoviesNavigation,
            MovieNavigation
        )
    }

    val route: String

    val arguments: List<NamedNavArgument> get() = emptyList()

    @Composable
    fun Content(
        navController: NavController,
        navBackStackEntry: NavBackStackEntry
    )
}
