package org.dmitrykochikiyan.gymtrack.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.dmitrykochikiyan.gymtrack.GymtrackAppState
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.SPLASH_SCREEN
import org.dmitrykochikiyan.gymtrack.presentation.view.GymPorgramScreen
import org.dmitrykochikiyan.gymtrack.presentation.view.SplashScreen

fun NavGraphBuilder.gymtrackGraph(appState: GymtrackAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(GYM_PROGRAM_SCREEN) {
        GymPorgramScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
}
