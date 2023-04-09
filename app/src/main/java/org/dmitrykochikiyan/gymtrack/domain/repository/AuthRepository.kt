package org.dmitrykochikiyan.gymtrack.domain.repository

import com.google.firebase.auth.FirebaseUser
import org.dmitrykochikiyan.gymtrack.domain.model.Response

typealias UserResponse = Response<FirebaseUser?>
interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun signIn(email: String, password: String): UserResponse
    suspend fun signInAnonymously(): UserResponse
    suspend fun signUp(email: String, password: String): UserResponse
    suspend fun signOut(): Response<Unit>
}