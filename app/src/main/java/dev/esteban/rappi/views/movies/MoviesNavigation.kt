package dev.esteban.rappi.views.movies

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.gson.Gson
import dev.esteban.rappi.navigation.ScreenNavigation
import dev.esteban.rappi.views.movie.MovieNavigation.MOVIE_DETAIL

object MoviesNavigation : ScreenNavigation {

    override val route = "movies"

    @Composable
    override fun Content(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        MoviesScreen { movie ->
            val movieJson = Uri.encode(Gson().toJson(movie))
            navController.navigate("$MOVIE_DETAIL/$movieJson")
        }
    }
}
