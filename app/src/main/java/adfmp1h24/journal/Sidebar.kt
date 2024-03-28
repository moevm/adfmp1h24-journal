package adfmp1h24.journal

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Sidebar() {
    /* открыт/закрыт */
    /* открывается свайпом вправо */
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    /* текущий экран */
    var screen by remember { mutableStateOf(ScreenType.Library) }
    var unsavedChanges by remember {
        mutableStateOf(false)
    }
    var handlerUnsavedOpened by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
        if (handlerUnsavedOpened){
            confirmDialog(desc = "Изменения не сохранены. Хотите выйти?",  {
                handlerUnsavedOpened = false
            }, { handlerUnsavedOpened = false; unsavedChanges = false; screen = ScreenType.Library })
        }
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Row(
                        modifier = Modifier.clickable{
                            /* сменить экран и закрыть sidebar */
                            if (unsavedChanges) {
                                handlerUnsavedOpened = true
                            } else {
                                screen = ScreenType.Library
                            }
                            scope.launch { drawerState.close() }
                        }
                    ) {
                        /* вкладка sidebar'a */
                        Text(text = "Library", fontSize = 20.sp, modifier = Modifier.padding(20.dp))
                    }
                    Row(
                        modifier = Modifier.clickable{
                            if (unsavedChanges) {
                                handlerUnsavedOpened = true
                            } else {
                                screen = ScreenType.Scratch
                            }
                            scope.launch { drawerState.close() }
                        }
                    ) {
                        Text(text = "Add new scratch", fontSize = 20.sp, modifier = Modifier.padding(20.dp))
                    }
                    Row(
                        modifier = Modifier.clickable{
                            if (unsavedChanges) {
                                handlerUnsavedOpened = true
                            } else {
                                screen = ScreenType.About
                            }
                            scope.launch { drawerState.close() }
                        }
                    ) {
                        Text(text = "About", fontSize = 20.sp, modifier = Modifier.padding(20.dp))
                    }
                }
            },
        ) {
            /* вычисление текущей страницы */
            Column(modifier = Modifier.padding(15.dp)) {
                /* если мы будем делать муршрутизацию по папкам, то нужно как то добавить её сюда */
                /* условно по маршруту "scatch/:id" */
                when (screen) {
                    ScreenType.Library -> MainScreen(
                        onNavigate = { screen = it },
                        sidebarState = drawerState,
                        scope = scope
                    )
                    ScreenType.Scratch -> ScratchScreen(isEdit = true, null, {screen = it}, unsavedChanges, { unsavedChanges = it })
                    ScreenType.About -> AboutScreen(navigate = { screen = it })
                }
            }
        }
    }
}

