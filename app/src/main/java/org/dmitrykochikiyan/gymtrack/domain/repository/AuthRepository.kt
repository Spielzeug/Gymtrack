package org.dmitrykochikiyan.gymtrack.domain.repository

import com.google.firebase.auth.FirebaseUser
import org.dmitrykochikiyan.gymtrack.domain.model.Response

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun signIn(email: String, password: String): Response<FirebaseUser?>
    suspend fun signInAnonymously(): Response<FirebaseUser?>
    suspend fun signUp(email: String, password: String): Response<FirebaseUser?>
    suspend fun signOut(): Response<Unit>
}