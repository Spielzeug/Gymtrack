package org.dmitrykochikiyan.gymtrack.domain.repository

import kotlinx.coroutines.flow.Flow
import org.dmitrykochikiyan.gymtrack.domain.model.GymProgram
import org.dmitrykochikiyan.gymtrack.domain.model.Response

typealias GymPrograms = List<GymProgram>
typealias GymProgramsResponse = Response<GymPrograms>

interface GymProgramRepository {
    fun getGymPrograms(): Flow<GymProgramsResponse>
}