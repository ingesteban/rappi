package dev.esteban.movies.data.datasource.remote

import dev.esteban.movies.data.datasource.remote.model.NetworkListMovies
import dev.esteban.movies.data.datasource.remote.model.NetworkMovie

interface MoviesNetworkDataSource {
    suspend fun getUpcoming(): NetworkListMovies

    suspend fun getTopRated(): NetworkListMovies

    suspend fun getMovieById(movieId: Int): NetworkMovie

    suspend fun getMovieVideosById(movieId: Int): NetworkListMovies
}
