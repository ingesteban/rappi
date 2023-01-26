package dev.esteban.movies.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.esteban.movies.data.datasource.local.entity.MovieEntity

@Dao
interface MoviesDao {
    @Query("Select * from movie WHERE type = :type")
    suspend fun getMoviesByType(type: String): List<MovieEntity>?

    @Query("Select * from movie")
    suspend fun getAllMovies(): List<MovieEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movie  WHERE type = :type")
    suspend fun clearByType(type: String)
}
