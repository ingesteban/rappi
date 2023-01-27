package dev.esteban.movies.data.datasource.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.esteban.movies.data.datasource.remote.model.NetworkListMovies
import dev.esteban.movies.data.datasource.remote.model.NetworkListVideos
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton
import dev.esteban.movies.BuildConfig.BASE_URL
import dev.esteban.movies.BuildConfig.MOVIE_UPCOMING
import dev.esteban.movies.BuildConfig.MOVIE_TOP_RATED
import dev.esteban.movies.BuildConfig.MOVIE_VIDEOS

/**
 * Retrofit API declaration for Movies Network API
 */
private interface MoviesApi {

    @GET(MOVIE_UPCOMING)
    suspend fun getUpcoming(): NetworkListMovies

    @GET(MOVIE_TOP_RATED)
    suspend fun getTopRated(): NetworkListMovies

    @GET(MOVIE_VIDEOS)
    suspend fun getMovieVideosById(@Path("movieId") movieId: Int): NetworkListVideos
}

/**
 * [Retrofit] backed [MoviesNetworkDataSource]
 */
@Singleton
class RetrofitMoviesNetwork @Inject constructor(networkJson: Json) : MoviesNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", "0d677b16a44d2b5a79edf325bc1ee9b7")
                        .build()
                    val requestBuilder = original.newBuilder()
                        .url(url)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()
        )
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(MoviesApi::class.java)


    override suspend fun getUpcoming(): NetworkListMovies = networkApi.getUpcoming()

    override suspend fun getTopRated(): NetworkListMovies = networkApi.getTopRated()

    override suspend fun getMovieVideosById(movieId: Int): NetworkListVideos =
        networkApi.getMovieVideosById(movieId)
}
