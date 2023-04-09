package org.dmitrykochikiyan.gymtrack.presentation.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.dmitrykochikiyan.gymtrack.domain.model.GymProgram

@Composable
fun GymProgramItem(gymProgram: GymProgram) {
    ElevatedCard(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = gymProgram.name,
                modifier = Modifier.padding(8.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = gymProgram.description,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
