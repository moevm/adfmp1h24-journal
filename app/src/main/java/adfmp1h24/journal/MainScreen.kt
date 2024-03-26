package adfmp1h24.journal

import Scratch
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import adfmp1h24.journal.ui.theme.Primary
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import getScratchList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigate: (ScreenType) -> Unit = {}, sidebarState: DrawerState, scope: CoroutineScope){

    /* поисковая строка */
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var openDescDialog by remember { mutableStateOf(false) }
    var currentDesc by remember { mutableStateOf("") }

    SearchBar(modifier = Modifier.fillMaxWidth(),
        query = text,
        shape = RoundedCornerShape(0.dp),
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            //active = it
        },
        leadingIcon = {
          Icon(
              /* открытие сайдбара */
              modifier = Modifier.clickable{ scope.launch { sidebarState.open() } },
              imageVector = Icons.Default.Menu,
              contentDescription = null
          )
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = SearchBarDefaults.colors(
            containerColor = Primary,
        )) {}

    /* Фильтры поиска: */
    Accordion(
        sections = listOf(
            CollapsableListSection(
                header = {
                    Text(text = "Filters", color = Primary)
                },
                rows = listOf(
                    { Filters() }
                )
            )
        )
    )
    val monthMass = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val scratchList = getScratchList(LocalContext.current)
    val groupedByMonth = mutableMapOf<Int, MutableList<Scratch>>()
    for(s in scratchList){
        if (text.length > 0 && !s.description.lowercase().contains(text.lowercase())){
            continue
        }
        val month = s.date.substring(5, 7).toInt()
        if (groupedByMonth[month] == null){
            groupedByMonth[month] = mutableListOf()
        }
        groupedByMonth[month]!!.add(s)
    }
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .heightIn(0.dp, 10000.dp)) {
        for (p in groupedByMonth) {
            /* Скрывающаяся вкладка с месяцами: */
            Accordion(
                sections = listOf(
                    CollapsableListSection(
                        /* заголовок вкладки */
                        header = {
                            Text(text = monthMass[p.key - 1], color = Primary)
                        },
                        /* контент вкладки */
                        rows = p.value.map { scratch ->
                            { ScratchCard(scratch = scratch, onNavigate = {currentDesc = scratch.description; openDescDialog = true}) }
                        }
                    ),
                )
            )
        }
    }
    if(openDescDialog){
        DescriptionDialog(desc = currentDesc) {
            openDescDialog = false

        }
    }
}