package dev.esteban.movies.domain.usecase

import dev.esteban.common.utils.Result
import dev.esteban.common.utils.data
import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.model.MovieType
import dev.esteban.movies.domain.repository.MoviesRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesUseCaseTest {

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupBase() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        getMoviesUseCase = GetMoviesUseCase(testCoroutineDispatcher, moviesRepository)
    }

    @Test
    fun shouldGetMoviesUpComing() = runBlocking {
        var result: Result<List<Movie>>? = null
        val listMoviesResponse: List<Movie> = mockk(relaxed = true)
        val params = GetMoviesUseCase.Parameters(MovieType.Upcoming)
        coEvery { moviesRepository.getMovies(params.type) } returns listMoviesResponse

        runTest(testCoroutineDispatcher) {
            result = getMoviesUseCase(params)
        }

        Assert.assertNotNull(result)
        Assert.assertEquals(result!!.data, listMoviesResponse)
        coVerify { moviesRepository.getMovies(params.type) }
        verify(exactly = 1) { listMoviesResponse.equals(listMoviesResponse) }
        confirmVerified(moviesRepository, listMoviesResponse)
    }

    @Test
    fun shouldGetMoviesTopRated() = runBlocking {
        var result: Result<List<Movie>>? = null
        val listMoviesResponse: List<Movie> = mockk(relaxed = true)
        val params = GetMoviesUseCase.Parameters(MovieType.TopRated)
        coEvery { moviesRepository.getMovies(params.type) } returns listMoviesResponse

        runTest(testCoroutineDispatcher) {
            result = getMoviesUseCase(params)
        }

        Assert.assertNotNull(result)
        Assert.assertEquals(result!!.data, listMoviesResponse)
        coVerify { moviesRepository.getMovies(params.type) }
        verify(exactly = 1) { listMoviesResponse.equals(listMoviesResponse) }
        confirmVerified(moviesRepository, listMoviesResponse)
    }
}
