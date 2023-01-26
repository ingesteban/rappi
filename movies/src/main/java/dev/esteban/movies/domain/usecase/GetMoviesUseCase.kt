package dev.esteban.movies.domain.usecase

import dev.esteban.common.network.Dispatcher
import dev.esteban.common.network.RappiDispatchers
import dev.esteban.common.utils.UseCase
import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.model.MovieType
import dev.esteban.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    @Dispatcher(RappiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val moviesRepository: MoviesRepository,
) : UseCase<GetMoviesUseCase.Parameters, List<Movie>>(ioDispatcher) {

    override suspend fun execute(parameters: Parameters): List<Movie> {
        return moviesRepository.getMovies(parameters.type)
    }

    data class Parameters(
        val type: MovieType
    )
}
