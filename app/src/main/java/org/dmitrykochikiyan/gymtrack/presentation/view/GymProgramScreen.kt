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

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.PROGRAM_ID
import org.dmitrykochikiyan.gymtrack.Routes.PROGRAM_WOURKOUTS_SCREEN
import org.dmitrykochikiyan.gymtrack.domain.model.GymProgram
import org.dmitrykochikiyan.gymtrack.domain.model.Response
import org.dmitrykochikiyan.gymtrack.presentation.view.components.GymProgramItem
import org.dmitrykochikiyan.gymtrack.presentation.view.components.GymtrackAppBar
import org.dmitrykochikiyan.gymtrack.presentation.viewmodel.GymProgramViewModel

@Composable
fun GymProgramScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GymProgramViewModel = hiltViewModel()
) {
    val programs by viewModel.gymProgramsResponseState.collectAsState()

    LaunchedEffect(Unit) { viewModel.initialize() }

    Scaffold(
        topBar = {
            GymtrackAppBar(
                currentView = "Programs",
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
        programs.let {
            when (it) {
                is Response.Success -> GymProgramsList(it.data, innerPadding, openAndPopUp)
                is Response.Failure -> Text(text = it.e?.message ?: "Error")
                is Response.Loading -> CircularProgressIndicator()
            }
        }
    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GymProgramsList(programs: List<GymProgram>, innerPadding: PaddingValues, openAndPopUp: (String, String) -> Unit) {
    LazyColumn(
        modifier = Modifier.consumeWindowInsets(innerPadding),
        contentPadding = innerPadding
    ) {
        items(programs) { program ->
            GymProgramItem(program) {
                openAndPopUp(
                    "$PROGRAM_WOURKOUTS_SCREEN?$PROGRAM_ID={${program.id}}",
                    GYM_PROGRAM_SCREEN
                )
            }
        }
    }
}

@Preview
@Composable
fun GymProgramViewPreview() {
    GymProgramScreen({ _, _ -> }, Modifier, hiltViewModel())
}