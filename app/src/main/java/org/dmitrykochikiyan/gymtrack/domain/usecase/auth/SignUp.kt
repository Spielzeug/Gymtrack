package org.dmitrykochikiyan.gymtrack.domain.usecase.auth

import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository

class SignUp(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepository.signUp(email, password)
}
