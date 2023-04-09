package org.dmitrykochikiyan.gymtrack.domain.model

import kotlin.time.Duration

data class ExerciseSet(
    val name: String = "",
    val description: String = "",
    val rest: Duration = Duration.ZERO,
    val intensity: Int = 0,
    val exercises: Map<Exercise, Int> = emptyMap()
)
