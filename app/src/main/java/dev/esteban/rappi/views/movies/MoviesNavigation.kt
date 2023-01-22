package dev.esteban.rappi.views.movies

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dev.esteban.rappi.navigation.ScreenNavigation


object MoviesNavigation : ScreenNavigation {

    override val route = "movies"

    @Composable
    override fun Content(navController: NavController, navBackStackEntry: NavBackStackEntry) {

        MoviesScreen(
            /*
            navigateToVacationDetails = { place ->
                val placeJson = Uri.encode(Gson().toJson(place))
                navController.navigate("$VACATIONS_DETAILS/$placeJson")
            }, navigateToCreateVacation = {
                navController.navigate(CreateVacationNavigation.route)
            }
             */
        )
    }
}
