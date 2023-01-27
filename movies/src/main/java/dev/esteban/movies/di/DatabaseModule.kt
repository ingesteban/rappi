package dev.esteban.movies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.esteban.movies.data.datasource.local.db.MoviesRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesMoviesDatabase(@ApplicationContext context: Context): MoviesRoomDatabase =
        MoviesRoomDatabase.build(context)
}