package adfmp1h24.journal

import adfmp1h24.journal.R.drawable.cardback
import adfmp1h24.journal.ui.theme.Primary
import adfmp1h24.journal.ui.theme.White
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScratchScreen(){
    Row {
        Text(
            /* TODO: название из хранилища скетча */
            text = "TRIP SCRATCH LABEL",
            modifier = Modifier
                .padding(15.dp)
                .weight(1f),
            fontSize = 25.sp,
            color = Primary
        )
        IconButton(onClick = { /*TODO: hide*/ }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null, tint = Primary)
        }
    }

    /* TODO: информация из хранилища скетча */
    Filters()

    /* TODO: описание из хранилища скетча */
    var description by remember { mutableStateOf("Trip description") }
    OutlinedTextField(
        value = description,
        onValueChange = { description= it },
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
        containerColor = White,
        focusedIndicatorColor = Primary,
        unfocusedIndicatorColor = Primary)
    )


    Text(
        text = "Gallery",
        modifier = Modifier
            .padding(15.dp),
        fontSize = 15.sp,
        color = Primary
    )

    /* TODO: фото из хранилища скетча */
    val photos = listOf(
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
        painterResource(id = cardback),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(photos) { photo ->
            Box(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)).height(90.dp)
            ) {
                Image(
                    painter = photo,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                )
            }
        }
    }
}