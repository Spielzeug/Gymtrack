package org.dmitrykochikiyan.gymtrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.repository.GymProgramsResponse
import org.dmitrykochikiyan.gymtrack.domain.usecase.program.UseCases
import javax.inject.Inject

@HiltViewModel
class GymProgramViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
    private val _gymProgramsResponseState = MutableStateFlow<GymProgramsResponse>(Response.Loading)
    val gymProgramsResponseState: StateFlow<GymProgramsResponse> = _gymProgramsResponseState


    init {
        getGymPrograms()
    }

    private fun getGymPrograms() = viewModelScope.launch {
        useCases.getGymPrograms().collect() {
            _gymProgramsResponseState.value = it
        }
    }
}
