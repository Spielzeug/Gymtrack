package org.dmitrykochikiyan.gymtrack.domain.usecase.auth

import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository

class SignInAnonymously(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.signInAnonymously()
}