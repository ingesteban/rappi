package dev.esteban.movies.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.esteban.movies.data.datasource.remote.MoviesNetworkDataSource
import dev.esteban.movies.data.datasource.remote.RetrofitMoviesNetwork
import dev.esteban.movies.data.repository.MoviesRepositoryImpl
import dev.esteban.movies.domain.repository.MoviesRepository
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsMoviesRepository(
        topicsRepository: MoviesRepositoryImpl
    ): MoviesRepository
}
