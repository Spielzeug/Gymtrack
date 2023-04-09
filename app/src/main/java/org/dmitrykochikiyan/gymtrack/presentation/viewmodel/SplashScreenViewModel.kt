package org.dmitrykochikiyan.gymtrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.SPLASH_SCREEN
import org.dmitrykochikiyan.gymtrack.domain.usecase.auth.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (authUseCases.getCurrentUser() == null)
            viewModelScope.launch { authUseCases.signInAnonymously() }

        openAndPopUp(GYM_PROGRAM_SCREEN, SPLASH_SCREEN)
    }
}