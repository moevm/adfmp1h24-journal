package adfmp1h24.journal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import adfmp1h24.journal.ui.theme.JournalTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JournalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    initApp()
                }
            }
        }
    }
}

@Composable
fun initApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()
    Sidebar(
        scope = scope,
        content = {
             Scaffold() {
                 var mod = Modifier.padding(it)
                 var screen by remember { mutableStateOf(ScreenType.Library) }
                 Column(modifier = mod.padding(15.dp)) {
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
         },
        drawerState = drawerState
    )
}