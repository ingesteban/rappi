package dev.esteban.rappi.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.esteban.movies.domain.model.Movie

class MovieParamType : NavType<Movie>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Movie {
        val myType = object : TypeToken<Movie>() {}.type
        return Gson().fromJson(value, myType)
    }

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putParcelable(key, value)
    }
}
