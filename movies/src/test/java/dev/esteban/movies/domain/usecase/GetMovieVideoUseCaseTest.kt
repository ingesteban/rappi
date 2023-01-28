package dev.esteban.movies.domain.usecase

import dev.esteban.common.utils.Result
import dev.esteban.common.utils.data
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

class GetMovieVideoUseCaseTest {
    private lateinit var getMovieVideoUseCase: GetMovieVideoUseCase

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setupBase() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        getMovieVideoUseCase = GetMovieVideoUseCase(testCoroutineDispatcher, moviesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldGetMovieVideo() = runBlocking {
        var result: Result<String>? = null
        val keyResponse = "F9UC9DY-vIU"
        val params = GetMovieVideoUseCase.Parameters(123)
        coEvery { moviesRepository.getMovieVideos(params.movieId) } returns keyResponse

        runTest(testCoroutineDispatcher) {
            result = getMovieVideoUseCase(params)
        }

        Assert.assertNotNull(result)
        Assert.assertEquals(result!!.data, keyResponse)
        coVerify { moviesRepository.getMovieVideos(params.movieId) }
        confirmVerified(moviesRepository)
    }
}
