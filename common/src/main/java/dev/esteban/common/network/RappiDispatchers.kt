package dev.esteban.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val rappiDispatchers: RappiDispatchers)

enum class RappiDispatchers { IO }
