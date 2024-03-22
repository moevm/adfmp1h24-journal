package adfmp1h24.journal

import adfmp1h24.journal.ui.theme.PreDark
import adfmp1h24.journal.ui.theme.Secondary
import adfmp1h24.journal.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScratchCard(onNavigate: (ScreenType) -> Unit = {}){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp),
        shape = RoundedCornerShape(0.dp),
        /* TODO: переход в скетч с данными в хранилище (условно по id) */
        onClick = { onNavigate(ScreenType.Scratch) }
    ){
        Box(
            modifier = with (Modifier) {
                fillMaxWidth()
                weight(1f, fill = true)
            }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                /* TODO: картинка из хранилища скетча */
                painter = painterResource(id = R.drawable.cardback),
                contentDescription = "back"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp),
                /* TODO: название из хранилища скетча */
                text = "Trip title",
                color = White
            )
        }
        Box(
            Modifier
                .background(
                    Brush.horizontalGradient(listOf(Secondary, PreDark))
                )
                .fillMaxWidth()
                .height(25.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                /* TODO: дата из хранилища скетча */
                text = "11.02.2024",
                color = White
            )
            IconButton(
                onClick = { /* TODO: поделиться контентом */ },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = White
                )
            }
        }
    }
}