package dev.esteban.movies.domain.usecase

import dev.esteban.common.network.Dispatcher
import dev.esteban.common.network.RappiDispatchers
import dev.esteban.common.utils.UseCase
import dev.esteban.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    @Dispatcher(RappiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val moviesRepository: MoviesRepository,
) : UseCase<GetMovieVideoUseCase.Parameters, String>(ioDispatcher) {

    override suspend fun execute(parameters: Parameters): String {
        return moviesRepository.getMovieVideos(parameters.movieId)
    }

    data class Parameters(
        val movieId: Int
    )
}
