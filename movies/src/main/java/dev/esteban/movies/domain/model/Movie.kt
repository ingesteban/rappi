package dev.esteban.movies.domain.model

import android.os.Parcelable
import dev.esteban.movies.data.datasource.local.entity.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int = 0,
    val adult: Boolean = false,
    val backdropPath: String? = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? = "",
    val releaseYear: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : Parcelable

fun Movie.toEntity(type: MovieType) = MovieEntity(
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
    voteCount = voteCount,
    type = type.name
)
