package dev.esteban.movies.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.esteban.movies.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = "",
    @ColumnInfo(name = "original_language")
    val originalLanguage: String = "",
    @ColumnInfo(name = "original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = "",
    @ColumnInfo(name = "release_year")
    val releaseYear: String = "",
    val title: String = "",
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0,
    var type: String = ""
)

fun MovieEntity.toDomain() = Movie(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseYear = releaseYear,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
