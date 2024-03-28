import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.security.AccessControlContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class Scratch(
    val label: String,
    val description: String,
    val date: String,
    val images: List<String>
)

fun clearData(context: Context) {
    context.getSharedPreferences("ScratchPreferences", Context.MODE_PRIVATE).edit().clear().commit()
}

@RequiresApi(Build.VERSION_CODES.O)
fun initDefaultData(context: Context) {
    if (getScratchList(context).isEmpty()) {
        saveScratch(context, Scratch(
            label = "Scratch title",
            description = "Trip description",
            date = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
            images = listOf()
        ))

        saveScratch(context, Scratch(
            label = "Scratch title II",
            description = "Fake description \n Fake description \n Fake description",
            date = LocalDate.now().plusMonths(-2).format(DateTimeFormatter.ISO_DATE),
            images = listOf()
        ))

        saveScratch(context, Scratch(
            label = "Scratch title III",
            description = "Fake description new \t Fake",
            date = LocalDate.now().plusMonths(-3).format(DateTimeFormatter.ISO_DATE),
            images = listOf()
        ))
    }
}

fun saveScratch(context: Context, newScratch: Scratch) {
    val sharedPreferences = context.getSharedPreferences("ScratchPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val currentList = getScratchList(context)
    val newList = mutableListOf<Scratch>(newScratch)
    newList.addAll(currentList)

    val json = gson.toJson(newList)
    editor.putString("scratchList", json)
    editor.apply()
}

fun getScratchList(context: Context): List<Scratch> {
    val sharedPreferences = context.getSharedPreferences("ScratchPreferences", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString("scratchList", null)
    val type = object : TypeToken<List<Scratch>>() {}.type
    return gson.fromJson(json, type) ?: emptyList()
}