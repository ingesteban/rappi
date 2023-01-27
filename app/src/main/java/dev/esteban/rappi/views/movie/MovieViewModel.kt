package dev.esteban.rappi.views.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.esteban.common.utils.Result.Error
import dev.esteban.common.utils.Result.Success
import dev.esteban.common.utils.ScreenState
import dev.esteban.common.utils.exceptionUnknown
import dev.esteban.movies.domain.usecase.GetMovieVideoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieUiState(
    val screenState: ScreenState = ScreenState.Empty,
    val errorMessage: String = String(),
    val keyVideo: String? = null,
)

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieUiState(screenState = ScreenState.Empty))
        private set

    fun getMovieVideo(movieId: Int) {
        uiState = uiState.copy(screenState = ScreenState.Loading)
        viewModelScope.launch {
            when (val result =
                getMovieVideoUseCase(GetMovieVideoUseCase.Parameters(movieId = movieId))) {
                is Success -> handleMovieVideo(result.data)
                is Error -> handleFailure(result.exception)
                else -> handleFailure(exceptionUnknown())
            }
        }
    }

    private fun handleMovieVideo(keyVideo: String) {
        uiState = uiState.copy(
            screenState = ScreenState.Success,
            keyVideo = keyVideo
        )
    }

    private fun handleFailure(exception: Exception) {
        uiState = uiState.copy(
            screenState = ScreenState.Error,
            errorMessage = exception.toString()
        )
    }
}
