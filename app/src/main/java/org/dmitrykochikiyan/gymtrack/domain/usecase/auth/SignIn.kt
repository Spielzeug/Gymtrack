package org.dmitrykochikiyan.gymtrack.domain.usecase.auth

import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository

class SignIn(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepository.signIn(email, password)
}
