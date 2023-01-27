package dev.esteban.movies.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkListVideos(
    val id: Int = 0,
    val results: List<NetworkVideo> = listOf(),
)

@Serializable
data class NetworkVideo(
    val key: String = "",
)
