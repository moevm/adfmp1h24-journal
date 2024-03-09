package adfmp1h24.journal

import adfmp1h24.journal.ui.theme.Primary
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun Accordion(
    sections: List<CollapsableListSection>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    expandByDefault: Boolean = true
) {
    val expandState =
        remember(sections) { sections.map { expandByDefault }.toMutableStateList() }

    LazyColumn(modifier, state = state) {
        sections.forEachIndexed { i, section ->
            val expand = expandState[i]
            item(key = "header_$i") {
                Row(modifier = Modifier.fillMaxWidth()) {
                    CollapsableListRow(
                        { section.header() },
                        Modifier.clickable { expandState[i] = !expand }
                            .weight(1f)
                            .padding(vertical = 10.dp)
                    )
                    val rotationAngle by animateFloatAsState(if (expand) 0f else -90f)
                    Icon(
                        modifier = Modifier.rotate(rotationAngle),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Primary
                    )
                }
            }
            if (expand) {
                items(section.rows) { row ->
                    CollapsableListRow(
                        { row() },
                        Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CollapsableListRow(content: @Composable () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        content()
    }
}

data class CollapsableListSection(
    val header: @Composable () -> Unit,
    val rows: List<@Composable () -> Unit>
)