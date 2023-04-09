package org.dmitrykochikiyan.gymtrack.domain.usecase.program

import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramRepository

class GetGymPrograms(private val gymProgramRepository: GymProgramRepository) {
    operator fun invoke() = gymProgramRepository.getGymPrograms()
}
