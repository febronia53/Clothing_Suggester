package com.fofa.clothing_suggester.util

import com.fofa.clothing_suggester.model.Interval
import com.fofa.clothing_suggester.model.Values
import com.fofa.clothing_suggester.network.DataManager

import org.json.JSONArray
import org.json.JSONObject

class NetworkUtils {
    val intervalsList = mutableListOf<Interval>()
    private val data = DataManager()

    fun getIntervalsJsonArrayFromJson(response: String): JSONArray{
        val jsonObject = JSONObject(response)
        return jsonObject.getJSONObject("data")
            .getJSONArray("timelines")
            .getJSONObject(0)
            .getJSONArray("intervals")
    }
    fun parseIntervals(intervals: JSONArray): List<Interval>{

        for(i in 0 until intervals.length()) {
            val interval = intervals.getJSONObject(i)
            val startTime = interval.getString("startTime")
            val values = interval.getJSONObject("values")
            val weatherValues = Values(
                humidity = values.getString("humidity").toString().toDouble(),
                temperature = values.getString("temperature").toString().toDouble(),
                maxTemperature = values.getString("temperatureMax").toString().toDouble(),
                windSpeed = values.getString("windSpeed").toString().toDouble()
            )
            val weatherType = data.getDatWeatherType(weatherValues)
            val clothesImageId = data.getClothesImageId(weatherType)
            intervalsList.add(Interval(startTime,weatherValues, weatherType, clothesImageId))

        }
        return intervalsList
    }

}