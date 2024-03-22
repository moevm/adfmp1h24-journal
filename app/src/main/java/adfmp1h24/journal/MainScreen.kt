package adfmp1h24.journal

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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigate: (ScreenType) -> Unit = {}, sidebarState: DrawerState, scope: CoroutineScope){

    /* поисковая строка */
    /* TODO: фильтрация по поисковой строке */
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

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
            active = it
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
            /* TODO: закрытие меню и поиск */
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
    /* Скрывающаяся вкладка с месяцами: */
    Accordion(
        sections = listOf(
            CollapsableListSection(
                /* заголовок вкладки */
                header = {
                    Text(text = "February", color = Primary)
                },
                /* контент вкладки */
                rows = listOf(
                    { ScratchCard(onNavigate) }
                )
            ),
            CollapsableListSection(
                header = {
                    Text(text = "January", color = Primary)
                },
                rows = listOf(
                    { ScratchCard(onNavigate) },
                    { ScratchCard(onNavigate) },
                    { ScratchCard(onNavigate) }
                )
            ),
        )
    )
}