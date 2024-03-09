package adfmp1h24.journal

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import adfmp1h24.journal.ui.theme.Primary
import adfmp1h24.journal.ui.theme.White
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Filters() {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, Primary),
        ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(top = 10.dp)) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = Primary
            )
            Text(text = "From: dd.mm.year")
        }
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = Primary
            )
            Text(text = "Till: dd.mm.year")
        }
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(bottom = 10.dp)) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = null,
                tint = Primary
            )
            Text(text = "Place: placeholder")
        }
    }
}