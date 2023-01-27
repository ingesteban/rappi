package dev.esteban.movies.domain.repository

import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.model.MovieType

interface MoviesRepository {
    suspend fun getMovies(type: MovieType): List<Movie>
    suspend fun getMovieVideos(id: Int): String
}
