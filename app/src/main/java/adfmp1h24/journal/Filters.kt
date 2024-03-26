package adfmp1h24.journal

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import adfmp1h24.journal.ui.theme.Primary
import adfmp1h24.journal.ui.theme.White
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Filters(isScratch: Boolean = false) {
    var fromDate by remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_DATE)) }
    var toDate by remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_DATE)) }
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        border = BorderStroke(1.dp, Primary),
        ) {
        /* TODO: активнный поиск по датам */
        if (!isScratch) {
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(top = 10.dp)) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Primary
                )
                Row(){
                    Text("From: ")
                    CustomDatePicker(
                        date = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE)
                    ) { fromDate = it.format(DateTimeFormatter.ISO_DATE) }
                }
            }
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Primary
                )
                Row(){
                    Text("To: ")
                    CustomDatePicker(
                        date = LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE)
                    ) { toDate = it.format(DateTimeFormatter.ISO_DATE) }
                }
            }
        } else {
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(top = 10.dp)) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Primary
                )
                Text(text = "dd.mm.year")
            }
        }
        /* TODO: эм... ну мы не успеем запилить поиск по месту, при всём желании */
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