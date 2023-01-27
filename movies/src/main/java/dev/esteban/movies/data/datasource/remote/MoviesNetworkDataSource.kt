package dev.esteban.movies.data.datasource.remote

import dev.esteban.movies.data.datasource.remote.model.NetworkListMovies
import dev.esteban.movies.data.datasource.remote.model.NetworkListVideos

interface MoviesNetworkDataSource {
    suspend fun getUpcoming(): NetworkListMovies

    suspend fun getTopRated(): NetworkListMovies

    suspend fun getMovieVideosById(movieId: Int): NetworkListVideos
}
