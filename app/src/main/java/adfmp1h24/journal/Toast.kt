package adfmp1h24.journal

import android.content.Context
import android.widget.Toast

fun mToast(context: Context, navigate: (ScreenType) -> Unit){
    Toast.makeText(context, "New card was added", Toast.LENGTH_LONG).show()
    navigate(ScreenType.Library)
}