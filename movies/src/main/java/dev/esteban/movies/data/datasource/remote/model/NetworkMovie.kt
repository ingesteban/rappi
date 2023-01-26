package dev.esteban.movies.data.datasource.remote.model

import android.annotation.SuppressLint
import dev.esteban.movies.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import dev.esteban.movies.BuildConfig.IMAGE_BASE_URL
import java.util.*

@Serializable
data class NetworkListMovies(
    val dates: NetworkDates = NetworkDates("", ""),
    val page: Int = 0,
    val results: List<NetworkMovie> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@Serializable
data class NetworkMovie(
    val id: Int = 0,
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String? = "",
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String? = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)

@Serializable
data class NetworkDates(
    val maximum: String = "",
    val minimum: String = ""
)

fun NetworkMovie.toDomain() = Movie(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = IMAGE_BASE_URL+posterPath,
    releaseYear = getYear(releaseDate),
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

@SuppressLint("SimpleDateFormat")
private fun getYear(releaseDate: String): String = try {
    SimpleDateFormat("yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(releaseDate) as Date)
} catch (e: Exception) {
    String()
}
