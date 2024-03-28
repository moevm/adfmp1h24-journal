package adfmp1h24.journal

import Scratch
import adfmp1h24.journal.R.drawable.cardback
import adfmp1h24.journal.ui.theme.Primary
import adfmp1h24.journal.ui.theme.White
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import saveScratch
import java.security.AccessController.getContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em

@Composable
private fun navigateWrap(changes: Boolean, screenType: ScreenType, navigate: (ScreenType) -> Unit) {
    if (!changes) {
        navigate(screenType)
    } else {
        confirmDialog(desc = "Изменения не сохранены. Хотите выйти?",  {}, {navigate(screenType)})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableScreen(scratch: Scratch ?= null, onChanges: (Boolean) -> Unit, navigate: (ScreenType) -> Unit) {
    var newScratch by remember {
        mutableStateOf(
            scratch ?: Scratch(
                label = "Scratch title",
                description = "Trip description",
                date = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
                images = listOf()
            )
        )
    }
    var title by remember { mutableStateOf(newScratch.label) }
    var description by remember { mutableStateOf(newScratch.description) }
    var pickedDate by remember { mutableStateOf(newScratch.date) }

    val context = LocalContext.current
    Row {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it; onChanges(true) },
            modifier = Modifier
                .weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = White,
                focusedIndicatorColor = Primary,
                unfocusedIndicatorColor = Primary
            )
        )
        IconButton(modifier = Modifier.padding(vertical = 5.dp), onClick = {
            newScratch = Scratch(
                title,
                description,
                pickedDate,
                images = listOf()
            )
            saveScratch(context, newScratch)
            mToast(context, navigate)
            onChanges(false)
        }) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Primary
            )
        }
    }
    OutlinedTextField(
        value = description,
        onValueChange = { description = it; onChanges(true) },
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = White,
            focusedIndicatorColor = Primary,
            unfocusedIndicatorColor = Primary
        )
    )
    CustomDatePicker(
        date = LocalDate.parse(pickedDate, DateTimeFormatter.ISO_DATE)
    ) { pickedDate = it.format(DateTimeFormatter.ISO_DATE); onChanges(true) }
}


fun ViewScreen(scratch: Scratch) {

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScratchScreen(isEdit: Boolean = false, scratch: Scratch ?= null, navigate: (ScreenType) -> Unit, changes: Boolean, onChanges: (Boolean) -> Unit){
    var isOpen by remember { mutableStateOf(false) }
    BackHandler(onBack = {
        if (changes){
            isOpen = true
        }
        else{
            navigate(ScreenType.Library)
        }
    })
    if (isEdit) {
        EditableScreen(null, onChanges, navigate)
    } else {
        Row {
            Text(
                /* TODO: название из хранилища скетча */
                text = "TRIP SCRATCH LABEL",
                modifier = Modifier
                    .padding(15.dp)
                    .weight(1f),
                fontSize = 25.sp,
                color = Primary
            )
            IconButton(onClick = { /*TODO: hide*/ }) {
                Icon(imageVector = Icons.Default.Create, contentDescription = null, tint = Primary)
            }
        }

        /* TODO: информация из хранилища скетча */
        Filters(isScratch = true, {}, {})

        /* TODO: описание из хранилища скетча */
        var description by remember { mutableStateOf("Trip description") }
        OutlinedTextField(
            value = description,
            onValueChange = { description= it },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = White,
                focusedIndicatorColor = Primary,
                unfocusedIndicatorColor = Primary)
        )


        Text(
            text = "Gallery",
            modifier = Modifier
                .padding(15.dp),
            fontSize = 15.sp,
            color = Primary
        )

        /* TODO: фото из хранилища скетча */
        val photos = listOf(
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
            painterResource(id = cardback),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(photos) { photo ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .height(90.dp)
                ) {
                    Image(
                        painter = photo,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                    )
                }
            }
        }
    }
    if (isOpen){
        confirmDialog(desc = "Изменения не сохранены. Хотите выйти?",  {
            isOpen = false
        }, { onChanges(false); navigate(ScreenType.Library) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun confirmDialog(desc: String, onDismiss: () -> Unit, onExit: () -> Unit){
    AlertDialog(onDismissRequest = onDismiss) {
        val shp = RoundedCornerShape(12.dp)
        Surface(Modifier.shadow(3.dp, shp), shp, Color.White) {
            Column {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .padding(10.dp)
                        .height(100.dp)
                ) {
                    Text(
                        desc,
                        fontSize = 6.em,
                        textAlign = TextAlign.Center
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Button(onClick = {onDismiss()}) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {onExit()}) {
                        Text(text = "Exit")
                    }
                }
            }
        }
    }
}