package com.br.vasques.tarot.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

const val DAY_MS = 24L * 60 * 60 * 1000

data class DailyDraw(val cardId: Int, val reversed: Boolean, val drawnAt: Long)
data class SpreadCard(val cardId: Int, val reversed: Boolean)
data class Reading(
    val id: Long,
    val createdAt: Long,
    val cards: List<SpreadCard>,
    val note: String
)

private val Context.dataStore by preferencesDataStore(name = "tarot_prefs")

class TarotRepository(private val context: Context) {

    private val dailyKey = stringPreferencesKey("daily_draw")
    private val readingsKey = stringPreferencesKey("readings")

    val daily: Flow<DailyDraw?> = context.dataStore.data.map { prefs ->
        prefs[dailyKey]?.let { json -> runCatching { parseDaily(json) }.getOrNull() }
    }

    val readings: Flow<List<Reading>> = context.dataStore.data.map { prefs ->
        prefs[readingsKey]?.let { json -> runCatching { parseReadings(json) }.getOrNull() }
            ?: emptyList()
    }

    suspend fun saveDaily(draw: DailyDraw) {
        val json = JSONObject()
            .put("cardId", draw.cardId)
            .put("reversed", draw.reversed)
            .put("drawnAt", draw.drawnAt)
            .toString()
        context.dataStore.edit { prefs -> prefs[dailyKey] = json }
    }

    suspend fun saveReadings(list: List<Reading>) {
        val array = JSONArray()
        list.forEach { reading ->
            val cardsJson = JSONArray()
            reading.cards.forEach { c ->
                cardsJson.put(JSONObject().put("cardId", c.cardId).put("reversed", c.reversed))
            }
            array.put(
                JSONObject()
                    .put("id", reading.id)
                    .put("createdAt", reading.createdAt)
                    .put("note", reading.note)
                    .put("cards", cardsJson)
            )
        }
        val json = array.toString()
        context.dataStore.edit { prefs -> prefs[readingsKey] = json }
    }

    private fun parseDaily(json: String): DailyDraw {
        val o = JSONObject(json)
        return DailyDraw(o.getInt("cardId"), o.getBoolean("reversed"), o.getLong("drawnAt"))
    }

    private fun parseReadings(json: String): List<Reading> {
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val o = array.getJSONObject(i)
            val cardsJson = o.getJSONArray("cards")
            Reading(
                id = o.getLong("id"),
                createdAt = o.getLong("createdAt"),
                note = o.getString("note"),
                cards = (0 until cardsJson.length()).map { j ->
                    val c = cardsJson.getJSONObject(j)
                    SpreadCard(
                        cardId = c.getInt("cardId"),
                        reversed = c.getBoolean("reversed")
                    )
                }
            )
        }
    }
}