package adfmp1h24.journal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
@Composable
fun AboutScreen(onNavigate: (ScreenType) -> Unit = {}) {
    Text(text = "Козиков Александр Евгеньевич 0304")
    Text(text = "Жиглов Дмитрий Сергеевич 0304")
    Text(text = "Докучаев Роман Дмитриевич 0304")
}