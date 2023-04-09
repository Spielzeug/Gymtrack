package org.dmitrykochikiyan.gymtrack.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.repository.AuthRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun signIn(email: String, password: String): Response<FirebaseUser?> =
        getResponseForAuthRequest(firebaseAuth.signInWithEmailAndPassword(email, password))

    override suspend fun signUp(email: String, password: String): Response<FirebaseUser?> =
        getResponseForAuthRequest(firebaseAuth.createUserWithEmailAndPassword(email, password))

    override suspend fun signInAnonymously(): Response<FirebaseUser?> =
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

    private suspend fun getResponseForAuthRequest(authTask: Task<AuthResult>): Response<FirebaseUser?> =
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