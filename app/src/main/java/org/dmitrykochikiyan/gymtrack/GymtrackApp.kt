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
package org.dmitrykochikiyan.gymtrack

import android.content.res.Resources
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.PROGRAM_WOURKOUTS_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.SPLASH_SCREEN
import org.dmitrykochikiyan.gymtrack.navigation.Navigator
import org.dmitrykochikiyan.gymtrack.navigation.gymtrackGraph
import org.dmitrykochikiyan.gymtrack.presentation.theme.GymtrackTheme
import org.dmitrykochikiyan.gymtrack.presentation.view.components.GymtrackAppDrawer

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

@Composable
fun GymtrackApp(
    widthSizeClass: WindowWidthSizeClass
) {
    GymtrackTheme {
        val appState = rememberAppState()
        val navigator = Navigator(appState.navigationController)
        ModalNavigationDrawer(
            drawerContent = {
                GymtrackAppDrawer(
                    currentRoute = appState.navigationController.currentDestination?.route ?: GYM_PROGRAM_SCREEN,
                    navigateToHome = { navigator.navigate(GYM_PROGRAM_SCREEN) },
                    navigateToWorkouts = { navigator.navigate(PROGRAM_WOURKOUTS_SCREEN) },
                    closeDrawer = { navigator.clearAndNavigate(GYM_PROGRAM_SCREEN) }
                )
            }
        ) {
            Scaffold(
                snackbarHost = { appState.snackbarHostState }
            ) {
                NavHost(
                    navController = appState.navigationController,
                    startDestination = SPLASH_SCREEN
                ) {
                    gymtrackGraph(navigator)
                }
            }
        }
    }
}







