package org.dmitrykochikiyan.gymtrack.domain.usecase.auth

import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository

class GetCurrentUser(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.currentUser
}
