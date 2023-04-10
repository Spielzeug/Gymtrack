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
