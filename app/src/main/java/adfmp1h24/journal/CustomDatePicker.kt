package adfmp1h24.journal

import adfmp1h24.journal.ui.theme.Primary
import adfmp1h24.journal.ui.theme.White
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomDatePicker(date: LocalDate, callback: (LocalDate) -> Unit) {
    var pickedDate by remember { mutableStateOf(date) }
    var isOpened by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = pickedDate.format(DateTimeFormatter.ISO_DATE) ,
        onValueChange = {},
        enabled = false,
        modifier = Modifier.fillMaxWidth().clickable {
            isOpened = true
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = White,
            focusedIndicatorColor = Primary,
            unfocusedIndicatorColor = Primary
        )
    )
    if (isOpened) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = { isOpened = false } ),
            config = CalendarConfig(
                yearSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
                selectedDate = date
            ) { newDate ->
                pickedDate = newDate
                callback(pickedDate)
            },
        )
    }
}