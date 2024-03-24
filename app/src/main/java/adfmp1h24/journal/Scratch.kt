import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.security.AccessControlContext
import java.time.LocalDate
import java.util.Date

class Scratch(
    val label: String,
    val description: String,
    val date: String,
    val images: List<String>
)

fun getScratchList(context: Context): List<Scratch> {
    val sharedPreferences = context.getSharedPreferences("ScratchPreferences", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString("scratchList", null)
    val type = object : TypeToken<List<Scratch>>() {}.type
    return gson.fromJson(json, type) ?: emptyList()
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