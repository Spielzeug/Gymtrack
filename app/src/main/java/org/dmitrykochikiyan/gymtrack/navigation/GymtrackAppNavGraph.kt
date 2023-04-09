package org.dmitrykochikiyan.gymtrack.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.SPLASH_SCREEN
import org.dmitrykochikiyan.gymtrack.presentation.view.GymProgramScreen
import org.dmitrykochikiyan.gymtrack.presentation.view.SplashScreen

fun NavGraphBuilder.gymtrackGraph(navigator: Navigator) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> navigator.navigateAndPopUp(route, popUp) })
    }

    composable(GYM_PROGRAM_SCREEN) {
        GymProgramScreen(openAndPopUp = { route, popUp -> navigator.navigateAndPopUp(route, popUp) })
    }
}
