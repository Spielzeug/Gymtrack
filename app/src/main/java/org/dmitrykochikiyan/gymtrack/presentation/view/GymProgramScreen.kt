package org.dmitrykochikiyan.gymtrack.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
                is Response.Success -> GymProgramsList(it.data, innerPadding)
                is Response.Failure -> Text(text = it.e?.message ?: "Error")
                is Response.Loading -> CircularProgressIndicator()
            }
        }
    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GymProgramsList(programs: List<GymProgram>, innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.consumeWindowInsets(innerPadding),
        contentPadding = innerPadding
    ) {
        items(programs) { program ->
            GymProgramItem(program)
        }
    }
}

@Preview
@Composable
fun GymProgramViewPreview() {
    GymProgramScreen({ _, _ -> }, Modifier, hiltViewModel())
}