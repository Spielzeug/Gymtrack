package org.dmitrykochikiyan.gymtrack.domain.model

class Workout(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val sets: List<ExerciseSet> = emptyList()
)
