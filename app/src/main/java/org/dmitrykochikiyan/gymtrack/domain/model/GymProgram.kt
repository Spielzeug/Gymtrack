package org.dmitrykochikiyan.gymtrack.domain.model

data class GymProgram(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val sets: List<ExerciseSet> = emptyList()
)