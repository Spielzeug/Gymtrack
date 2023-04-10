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