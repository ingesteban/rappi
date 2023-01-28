package dev.esteban.rappi.views.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.esteban.common.utils.Result
import dev.esteban.common.utils.Result.Error
import dev.esteban.common.utils.Result.Success
import dev.esteban.common.utils.ScreenState
import dev.esteban.common.utils.exceptionUnknown
import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.model.MovieType.TopRated
import dev.esteban.movies.domain.model.MovieType.Upcoming
import dev.esteban.movies.domain.usecase.GetMoviesUseCase
import dev.esteban.movies.domain.usecase.GetMoviesUseCase.Parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class MoviesUiState(
    val screenState: ScreenState = ScreenState.Empty,
    val errorMessage: String = String(),
    val upcomingMovies: List<Movie>? = null,
    val topRatedMovies: List<Movie>? = null,
    val recommendedMovies: List<Movie>? = null,
    val yearsFilter: List<String>? = null,
    val languageFilter: List<String>? = null,
)

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private companion object {
        const val RECOMMENDED_MOVIES = 6
        const val DEFAULT_YEAR_FILTER = "Cualquier año"
        const val DEFAULT_LANGUAGE_FILTER = "Cualquier idioma"
    }

    var uiState by mutableStateOf(MoviesUiState(screenState = ScreenState.Empty))
        private set

    private var recommendedMovies: List<Movie> = emptyList()

    private var filterYear = "Cualquier año"

    private var filterLanguage = "Cualquier idioma"

    fun getMovies() {
        viewModelScope.launch {
            val topRatedMovies = withContext(Dispatchers.Default) {
                getMoviesUseCase(Parameters(TopRated))
            }
            val upcomingMovies = withContext(Dispatchers.Default) {
                getMoviesUseCase(Parameters(Upcoming))
            }
            processData(topRatedMovies, upcomingMovies)
        }
    }

    fun resetFilters() {
        filterYear = "Cualquier año"
        filterLanguage = "Cualquier idioma"
        filterRecommendedMovies()
    }

    fun filterRecommendedMoviesByYear(year: String) {
        filterYear = year
        filterRecommendedMovies()
    }

    fun filterRecommendedMoviesByLanguage(language: String) {
        filterLanguage = language
        filterRecommendedMovies()
    }

    private fun filterRecommendedMovies() {
        var recommendedMoviesFiltered = recommendedMovies
        recommendedMoviesFiltered = if (filterYear != DEFAULT_YEAR_FILTER) {
            recommendedMoviesFiltered.filter { it.releaseYear == filterYear }
        } else {
            recommendedMoviesFiltered
        }

        recommendedMoviesFiltered = if (filterLanguage != DEFAULT_LANGUAGE_FILTER) {
            recommendedMoviesFiltered.filter { it.originalLanguage == filterLanguage }
        } else {
            recommendedMoviesFiltered
        }

        uiState = uiState.copy(
            screenState = ScreenState.Success,
            recommendedMovies = recommendedMoviesFiltered.take(RECOMMENDED_MOVIES),
        )
    }

    private fun processData(
        topRatedMoviesResponse: Result<List<Movie>>,
        upcomingMoviesResponse: Result<List<Movie>>,
    ) {
        when (topRatedMoviesResponse) {
            is Success -> handleTopRatedMovies(topRatedMoviesResponse.data)
            is Error -> handleFailure(topRatedMoviesResponse.exception)
            else -> handleFailure(exceptionUnknown())
        }
        when (upcomingMoviesResponse) {
            is Success -> handleUpcomingMovies(upcomingMoviesResponse.data)
            is Error -> handleFailure(upcomingMoviesResponse.exception)
            else -> handleFailure(exceptionUnknown())
        }
    }

    private fun handleUpcomingMovies(upcomingMovies: List<Movie>) {
        uiState = uiState.copy(
            screenState = ScreenState.Error,
            upcomingMovies = upcomingMovies
        )
    }

    private fun handleTopRatedMovies(topRatedMovies: List<Movie>) {
        recommendedMovies = topRatedMovies
        val recommendedMoviesFiltered = recommendedMovies.take(RECOMMENDED_MOVIES)
        val yearsFilter = mutableListOf(DEFAULT_YEAR_FILTER)
        yearsFilter.addAll(topRatedMovies.distinctBy { it.releaseYear }.map { it.releaseYear })
        val languageFilter = mutableListOf(DEFAULT_LANGUAGE_FILTER)
        languageFilter.addAll(topRatedMovies.distinctBy { it.originalLanguage }
            .map { it.originalLanguage })
        uiState = uiState.copy(
            screenState = ScreenState.Success,
            topRatedMovies = topRatedMovies,
            recommendedMovies = recommendedMoviesFiltered,
            yearsFilter = yearsFilter,
            languageFilter = languageFilter,
        )
    }

    private fun handleFailure(exception: Exception) {
        uiState = uiState.copy(
            screenState = ScreenState.Error,
            errorMessage = exception.toString()
        )
    }
}
