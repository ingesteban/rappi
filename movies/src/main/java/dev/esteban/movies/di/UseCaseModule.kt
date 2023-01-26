package dev.esteban.movies.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.esteban.common.network.Dispatcher
import dev.esteban.common.network.RappiDispatchers
import dev.esteban.common.utils.UseCase
import dev.esteban.movies.data.datasource.local.dao.MoviesDao
import dev.esteban.movies.data.datasource.local.db.MoviesRoomDatabase
import dev.esteban.movies.domain.model.Movie
import dev.esteban.movies.domain.repository.MoviesRepository
import dev.esteban.movies.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesNetworkJson(
        @Dispatcher(RappiDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        moviesRepository: MoviesRepository
    ): GetMoviesUseCase =
        GetMoviesUseCase(ioDispatcher = ioDispatcher, moviesRepository = moviesRepository)
}
