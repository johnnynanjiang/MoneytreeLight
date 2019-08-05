package io.github.johnnynanjiang.android.moneytreelight.util

import android.content.Context
import org.json.JSONObject
import java.lang.Exception
import java.lang.RuntimeException

class LocalJSONFileLoader(private val applicationContext: Context) {
    fun getJSONObject(filepath: String): JSONObject =
        JSONObject(loadJSONFile(filepath))

    private fun loadJSONFile(filepath: String): String =
        try {
            applicationContext.assets.open(filepath).bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("JSON file not found at $filepath")
        }
}