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
    private val snackbarHostState: SnackbarHostState,
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

    fun popUp() {
        navigationController.popBackStack()
    }

    fun navigate(route: String) {
        navigationController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navigationController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navigationController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}