package dev.esteban.movies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.esteban.movies.data.datasource.local.dao.MoviesDao
import dev.esteban.movies.data.datasource.local.db.MoviesRoomDatabase

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesAuthorDao(database: MoviesRoomDatabase): MoviesDao = database.moviesDao()
}
