package org.dmitrykochikiyan.gymtrack

import android.content.res.Resources
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import org.dmitrykochikiyan.gymtrack.Routes.SPLASH_SCREEN
import org.dmitrykochikiyan.gymtrack.navigation.Navigator
import org.dmitrykochikiyan.gymtrack.navigation.gymtrackGraph
import org.dmitrykochikiyan.gymtrack.presentation.theme.GymtrackTheme

@Composable
fun GymtrackApp() {
    GymtrackTheme {
        Surface {
            val appState = rememberAppState()
            Scaffold(
                snackbarHost = { appState.snackbarHostState }
            ) {
                NavHost(
                    navController = appState.navigationController,
                    startDestination = SPLASH_SCREEN
                ) {
                    gymtrackGraph(Navigator(appState.navigationController))
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    navigationController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    resources: Resources = rememberResources()
) = remember { GymtrackAppState(navigationController, snackbarHostState, coroutineScope, resources) }

@Composable
@ReadOnlyComposable
fun rememberResources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}




