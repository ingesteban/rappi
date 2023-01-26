package dev.esteban.movies.data.datasource.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromBaits(value: List<String>?): String? {
        val type = object : TypeToken<List<String>>() {}.type
        return value?.let { Gson().toJson(value, type) }
    }

    @TypeConverter
    fun toBaits(value: String?): List<String>? {
        value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(value, type)
        } ?: return emptyList()
    }
}