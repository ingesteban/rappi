package dev.esteban.movies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.esteban.common.network.Dispatcher
import dev.esteban.common.network.RappiDispatchers
import dev.esteban.movies.domain.repository.MoviesRepository
import dev.esteban.movies.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
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
