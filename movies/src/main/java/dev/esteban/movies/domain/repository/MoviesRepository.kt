package dev.esteban.movies.domain.repository

import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.model.MovieType

interface MoviesRepository {
    suspend fun getMovies(type: MovieType): List<Movie>
    suspend fun getMovieById(id: Int): Movie
}
