package org.dmitrykochikiyan.gymtrack.domain.repository

import kotlinx.coroutines.flow.Flow
import org.dmitrykochikiyan.gymtrack.domain.model.GymProgram
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.model.Workout

typealias GymPrograms = List<GymProgram>
typealias GymProgramsResponse = Response<GymPrograms>
typealias Workouts = List<Workout>
typealias WorkoutsResponse = Response<Workouts>

interface GymProgramRepository {
    fun getGymPrograms(): Flow<GymProgramsResponse>
    fun getWorkouts(gymProgramId: String): Flow<WorkoutsResponse>
}