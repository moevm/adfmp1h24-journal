package adfmp1h24.journal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Sidebar(
    content: @Composable () -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onNavigate: (ScreenType) -> Unit = {}
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier.clickable{
                        onNavigate(ScreenType.Library)
                        scope.launch { drawerState.close() }
                    }
                ) {
                    Text(text = "Library")
                }
                Row(
                    modifier = Modifier.clickable{
                        onNavigate(ScreenType.Scratch)
                    }
                ) {
                    Text(text = "Add new scratch")
                }
                Row(
                    modifier = Modifier.clickable{
                        onNavigate(ScreenType.About)
                        scope.launch { drawerState.close() }
                    }
                ) {
                    Text(text = "About")
                }
            }
        },
    ) {
        content()
    }
}

