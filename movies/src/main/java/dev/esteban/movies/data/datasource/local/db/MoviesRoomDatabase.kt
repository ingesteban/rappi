package dev.esteban.movies.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.esteban.movies.data.datasource.local.dao.MoviesDao
import dev.esteban.movies.data.datasource.local.entity.MovieEntity

private const val MOVIES_DB_VERSION = 1
private const val MOVIES_DB_NAME = "movies.db"

@Database(entities = [MovieEntity::class], version = MOVIES_DB_VERSION, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context, MoviesRoomDatabase::class.java, MOVIES_DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
