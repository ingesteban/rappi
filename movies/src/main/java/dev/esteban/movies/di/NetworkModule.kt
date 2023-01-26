package dev.esteban.movies.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.esteban.movies.data.datasource.remote.MoviesNetworkDataSource
import dev.esteban.movies.data.datasource.remote.RetrofitMoviesNetwork
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsNiaNetwork(retrofitMoviesNetwork: RetrofitMoviesNetwork): MoviesNetworkDataSource

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}
