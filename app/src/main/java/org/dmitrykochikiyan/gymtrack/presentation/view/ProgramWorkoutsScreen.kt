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
package org.dmitrykochikiyan.gymtrack.presentation.view

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.domain.model.Workout
import org.dmitrykochikiyan.gymtrack.presentation.view.components.GymtrackAppBar
import org.dmitrykochikiyan.gymtrack.presentation.view.components.WorkoutItem
import org.dmitrykochikiyan.gymtrack.presentation.viewmodel.ProgramWorkoutsViewModel

@Composable
fun ProgramWorkoutsScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProgramWorkoutsViewModel = hiltViewModel(),
    programId: String
) {
    val workouts by viewModel.workoutsResponseState.collectAsState()

    LaunchedEffect(Unit) { viewModel.initialize(programId) }
    Scaffold(
        topBar = {
            GymtrackAppBar(
                currentView = "Workouts",
                modifier = Modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { openAndPopUp("AddProgram", "AddProgram") }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Create program"
                )
            }
        }
    ) { innerPadding ->
        workouts.let {
            when (it) {
                is Response.Success -> WorkoutsList(it.data, innerPadding, openAndPopUp)
                is Response.Failure -> Text(text = it.e?.message ?: "Error")
                is Response.Loading -> CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WorkoutsList(workouts: List<Workout>, innerPadding: PaddingValues, openAndPopUp: (String, String) -> Unit) {
    LazyColumn(
        modifier = Modifier.consumeWindowInsets(innerPadding),
        contentPadding = innerPadding
    ) {
        items(workouts) { workout ->
            WorkoutItem(workout) {
                //TODO: navigate to workout screen
            }
        }
    }
}