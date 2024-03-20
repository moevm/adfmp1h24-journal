package adfmp1h24.journal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun Sidebar() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var screen by remember { mutableStateOf(ScreenType.Library) }
    val scope = rememberCoroutineScope()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Row(
                        modifier = Modifier.clickable{
                            screen = ScreenType.Library
                            scope.launch { drawerState.close() }
                        }
                    ) {
                        Text(text = "Library", fontSize = 20.sp, modifier = Modifier.padding(20.dp))
                    }
                    Row(
                        modifier = Modifier.clickable{
                            screen = ScreenType.Scratch
                        }
                    ) {
                        Text(text = "Add new scratch", fontSize = 20.sp, modifier = Modifier.padding(20.dp))
                    }
                    Row(
                        modifier = Modifier.clickable{
                            screen = ScreenType.About
                            scope.launch { drawerState.close() }
                        }
                    ) {
                        Text(text = "About", fontSize = 20.sp, modifier = Modifier.padding(20.dp))
                    }
                }
            },
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                when (screen) {
                    ScreenType.Library -> MainScreen(
                        onNavigate = { screen = it },
                        sidebarState = drawerState,
                        scope = scope
                    )
                    ScreenType.Scratch -> ScratchScreen(onNavigate = { screen = it })
                    ScreenType.About -> AboutScreen(onNavigate = { screen = it })
                }
            }
        }
    }
}

