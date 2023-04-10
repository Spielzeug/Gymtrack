/*
Copyright 2022 Google LLC
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    https://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.dmitrykochikiyan.gymtrack.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository
import org.dmitrykochikiyan.gymtrack.domain.repository.UserResponse
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun signIn(email: String, password: String): UserResponse =
        getResponseForAuthRequest(firebaseAuth.signInWithEmailAndPassword(email, password))

    override suspend fun signUp(email: String, password: String): UserResponse =
        getResponseForAuthRequest(firebaseAuth.createUserWithEmailAndPassword(email, password))

    override suspend fun signInAnonymously(): UserResponse =
        getResponseForAuthRequest(firebaseAuth.signInAnonymously())

    override suspend fun signOut(): Response<Unit> {
        return currentUser?.let {
            if (it.isAnonymous) {
                it.delete().await()
            }
            firebaseAuth.signOut()
            firebaseAuth.signInAnonymously()
            Response.Success(Unit)
        } ?: Response.Failure(Exception("User is not signed in"))
    }

    private suspend fun getResponseForAuthRequest(authTask: Task<AuthResult>): UserResponse =
        suspendCoroutine { continuation ->
            authTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(Response.Success(firebaseAuth.currentUser))
                } else {
                    val exception = task.exception
                    if (exception != null) {
                        continuation.resume(Response.Failure(exception))
                    } else {
                        continuation.resume(Response.Failure(Exception("Unknown error")))
                    }
                }
            }
        }
}