package dev.esteban.movies.data.repository

import dev.esteban.movies.data.datasource.local.dao.MoviesDao
import dev.esteban.movies.data.datasource.remote.MoviesNetworkDataSource
import dev.esteban.movies.data.datasource.local.entity.toDomain
import dev.esteban.movies.data.datasource.remote.model.toDomain
import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.model.MovieType
import dev.esteban.movies.domain.model.toEntity
import dev.esteban.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val moviesNetworkDataSource: MoviesNetworkDataSource,
) : MoviesRepository {
    override suspend fun getMovies(type: MovieType): List<Movie> {
        val moviesLocal = moviesDao.getMoviesByType(type.name)?.map {
            it.toDomain()
        }

        val movies = if (moviesLocal.isNullOrEmpty()) {
            if (type == MovieType.Upcoming) {
                moviesNetworkDataSource.getUpcoming().results.map {
                    it.toDomain()
                }
            } else {
                moviesNetworkDataSource.getTopRated().results.map {
                    it.toDomain()
                }
            }
        } else {
            moviesLocal
        }
        moviesDao.clearByType(type.name)
        moviesDao.saveAllMovies(movies.map { it.toEntity(type) })

        return movies
    }

    override suspend fun getMovieVideos(id: Int): String {
        val movieVideos = moviesNetworkDataSource.getMovieVideosById(id)
        return movieVideos.results.first().key
    }
}
