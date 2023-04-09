package org.dmitrykochikiyan.gymtrack

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class GymtrackAppState(
    val navigationController: NavHostController,
    val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
    private val resources: Resources
) {
    init {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = "Hello from Gymtrack!",
                actionLabel = "Dismiss"
            )
        }
    }


}