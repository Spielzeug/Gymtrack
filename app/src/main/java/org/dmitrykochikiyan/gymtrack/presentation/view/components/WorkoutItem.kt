package org.dmitrykochikiyan.gymtrack.presentation.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.dmitrykochikiyan.gymtrack.domain.model.Workout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutItem(workout: Workout, onClick: () -> Unit) {
    ElevatedCard(
        //TODO: generalize dimensions
        modifier = Modifier.padding(8.dp).height(100.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = workout.name,
                modifier = Modifier.padding(8.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = workout.description,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}