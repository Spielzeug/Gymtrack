package org.dmitrykochikiyan.gymtrack.domain.usecase.program

import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramRepository

class GetProgramWorkouts(private val gymProgramRepository: GymProgramRepository) {
    operator fun invoke(programId: String) = gymProgramRepository.getWorkouts(programId)
}
