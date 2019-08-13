package io.github.johnnynanjiang.android.moneytreelight.util

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.json.JSONArray

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class LocalJSONFileLoaderTest {
    @Test
    fun shouldLoadJSONObjectFromAssetFile() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val localJSONFileLoader = LocalJSONFileLoader(appContext)
        val jsonObject = localJSONFileLoader.getJSONObject("json/accounts.json")

        assertEquals(3, (jsonObject.get("accounts") as JSONArray).length())
    }
}
