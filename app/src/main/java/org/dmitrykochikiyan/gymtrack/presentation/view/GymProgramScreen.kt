package org.dmitrykochikiyan.gymtrack.presentation.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.dmitrykochikiyan.gymtrack.presentation.viewmodel.GymProgramViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymPorgramScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GymProgramViewModel = hiltViewModel()
) {

}


