package org.dmitrykochikiyan.gymtrack.presentation.view.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymtrackAppBar(
    currentView: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentView) },
        modifier = modifier,
    )
}