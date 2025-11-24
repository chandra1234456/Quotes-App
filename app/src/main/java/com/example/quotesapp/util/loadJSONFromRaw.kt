package com.example.quotesapp.util

import android.content.Context
import androidx.annotation.RawRes
import com.example.quotesapp.presentation.data.CategoriesItem
import org.json.JSONObject

fun loadJSONFromRaw(context: Context, @RawRes resourceId: Int): String? {
    return try {
        val inputStream = context.resources.openRawResource(resourceId)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}

fun loadQuotesFromRaw(context: Context, @RawRes resourceId: Int): List<CategoriesItem> {
    val jsonString = context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    val quotesArray = jsonObject.getJSONArray("quotes")

    val quotesList = mutableListOf<CategoriesItem>()
    for (i in 0 until quotesArray.length()) {
        val item = quotesArray.getJSONObject(i)
        quotesList.add(
            CategoriesItem(
                quote = item.getString("quote"),
                author = item.getString("author"),
                quoteType = item.getString("quoteType")
            )
        )
    }
    return quotesList
}

