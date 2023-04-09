package org.dmitrykochikiyan.gymtrack.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramsResponse
import org.dmitrykochikiyan.gymtrack.domain.usecase.program.UseCases
import javax.inject.Inject

@HiltViewModel
class GymProgramViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
    var gymProgramsResponse by mutableStateOf<GymProgramsResponse>(Response.Loading)
        private set

    init {
        getGymPrograms()
    }

    private fun getGymPrograms() = viewModelScope.launch {
        useCases.getGymPrograms().collect() {
            gymProgramsResponse = it
        }
    }
}
