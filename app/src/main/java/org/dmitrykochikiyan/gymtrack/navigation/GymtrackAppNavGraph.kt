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
package org.dmitrykochikiyan.gymtrack.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.PROGRAM_ID
import org.dmitrykochikiyan.gymtrack.Routes.PROGRAM_WOURKOUTS_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.SPLASH_SCREEN
import org.dmitrykochikiyan.gymtrack.presentation.view.GymProgramScreen
import org.dmitrykochikiyan.gymtrack.presentation.view.ProgramWorkoutsScreen
import org.dmitrykochikiyan.gymtrack.presentation.view.SplashScreen

fun NavGraphBuilder.gymtrackGraph(navigator: Navigator) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> navigator.navigateAndPopUp(route, popUp) })
    }

    composable(GYM_PROGRAM_SCREEN) {
        GymProgramScreen(openAndPopUp = { route, popUp -> navigator.navigateAndPopUp(route, popUp) })
    }

    composable(route = PROGRAM_WOURKOUTS_SCREEN,
        arguments = listOf(
            navArgument(PROGRAM_ID) {
                type = NavType.StringType
                nullable = false
                defaultValue = ""
            }
        )) {
        ProgramWorkoutsScreen(openAndPopUp = { route, popUp -> navigator.navigateAndPopUp(route, popUp) })
    }
}
