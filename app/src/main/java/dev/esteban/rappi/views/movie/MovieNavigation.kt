package dev.esteban.rappi.views.movie

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.navArgument
import dev.esteban.movies.domain.model.Movie
import dev.esteban.rappi.navigation.ScreenNavigation
import dev.esteban.rappi.utils.MovieParamType

object MovieNavigation : ScreenNavigation {

    const val MOVIE_DETAIL = "movie_detail"
    private const val MOVIE_DATA = "movie_data"

    override val route = "$MOVIE_DETAIL/{$MOVIE_DATA}"

    override val arguments = listOf(
        navArgument(MOVIE_DATA) { type = MovieParamType() }
    )

    @Composable
    override fun Content(
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
    ) {
        val movie = navBackStackEntry.arguments?.getParcelable<Movie>(MOVIE_DATA)
        MovieScreen(movie = movie) {
            navController.popBackStack()
        }
    }
}
