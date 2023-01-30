package dev.esteban.movies.data.repository

import dev.esteban.movies.data.datasource.local.dao.MoviesDao
import dev.esteban.movies.data.datasource.remote.MoviesNetworkDataSource
import dev.esteban.movies.data.datasource.remote.model.NetworkListVideos
import dev.esteban.movies.data.datasource.remote.model.NetworkVideo
import io.mockk.*
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesRepositoryImplTest {

    private companion object {
        const val MOVIE_ID = 123
    }

    private val moviesDao: MoviesDao = mockk()
    private val moviesNetworkDataSource: MoviesNetworkDataSource = mockk()
    private lateinit var repositoryImpl: MoviesRepositoryImpl

    @Before
    fun setupBase() {
        MockKAnnotations.init(this)
        repositoryImpl = MoviesRepositoryImpl(moviesDao, moviesNetworkDataSource)
    }

    @Test
    fun shouldGetMovieVideosSuccess() {
        val response: NetworkListVideos = mockk(relaxed = true)
        val results: List<NetworkVideo> = mockk(relaxed = true)
        val networkVideo: NetworkVideo = mockk(relaxed = true)
        val keyResponse = "F9UC9DY-vIU"
        var result: String? = null

        coEvery { moviesNetworkDataSource.getMovieVideosById(MOVIE_ID) } returns response
        every { response.results } returns results
        every { results.firstOrNull() } returns networkVideo
        every { networkVideo.key } returns keyResponse

        runTest(UnconfinedTestDispatcher()) {
            result = repositoryImpl.getMovieVideos(MOVIE_ID)
        }

        TestCase.assertNotNull(result)
        TestCase.assertEquals(result, keyResponse)
        coVerify { moviesNetworkDataSource.getMovieVideosById(MOVIE_ID) }
        verify { response.results }
        verify { results.firstOrNull() }
        verify { networkVideo.key }
        confirmVerified(moviesNetworkDataSource, response, results, networkVideo)
    }

    @Test
    fun shouldGetMovieVideosUnsuccessfully() {
        val response: NetworkListVideos = mockk(relaxed = true)
        val results: List<NetworkVideo> = emptyList()
        var result: String? = null

        coEvery { moviesNetworkDataSource.getMovieVideosById(MOVIE_ID) } returns response
        every { response.results } returns results

        runTest(UnconfinedTestDispatcher()) {
            result = repositoryImpl.getMovieVideos(MOVIE_ID)
        }

        TestCase.assertNotNull(result)
        TestCase.assertEquals(result, String())
        coVerify { moviesNetworkDataSource.getMovieVideosById(MOVIE_ID) }
        verify { response.results }
        confirmVerified(moviesNetworkDataSource, response)
    }
}
