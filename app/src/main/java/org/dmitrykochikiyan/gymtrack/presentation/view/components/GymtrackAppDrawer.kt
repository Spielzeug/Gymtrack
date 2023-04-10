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
package org.dmitrykochikiyan.gymtrack.presentation.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.dmitrykochikiyan.gymtrack.Routes.GYM_PROGRAM_SCREEN
import org.dmitrykochikiyan.gymtrack.Routes.PROGRAM_WOURKOUTS_SCREEN

@Composable
fun GymtrackAppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToWorkouts: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier) {
        NavigationDrawerItem(
            label = { Text("Programs") },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == GYM_PROGRAM_SCREEN,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Workouts") },
            icon = { Icon(Icons.Filled.ListAlt, null) },
            selected = currentRoute == PROGRAM_WOURKOUTS_SCREEN,
            onClick = { navigateToWorkouts(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}