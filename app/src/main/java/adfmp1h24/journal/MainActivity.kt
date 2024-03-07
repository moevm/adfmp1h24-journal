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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var screen by remember { mutableStateOf(ScreenType.Library) }

    Scaffold() {
        var mod = Modifier.padding(it)
        Column(modifier = mod.padding(15.dp)) {
            when (screen) {
                ScreenType.Library -> MainScreen(onNavigate = { screen = it })
                ScreenType.Scratch -> ScratchScreen(onNavigate = { screen = it })
            }
        }
    }
}