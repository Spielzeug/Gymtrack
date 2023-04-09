package org.dmitrykochikiyan.gymtrack.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.usecase.auth.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    private val _email = MutableLiveData("")
    private val _password = MutableLiveData("")

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    suspend fun signIn(email: String, password: String): Response<FirebaseUser?> {
        return authUseCases.signIn(email, password)
    }

    suspend fun signUp(email: String, password: String): Response<FirebaseUser?> {
        return authUseCases.signUp(email, password)
    }

    suspend fun signOut(): Response<Unit> {
        return authUseCases.signOut()
    }
}