package com.fofa.clothing_suggester.network

import com.fofa.clothing_suggester.model.Interval
import com.fofa.clothing_suggester.util.Constants.APIKEY
import com.fofa.clothing_suggester.util.Constants.BASE_URL
import com.fofa.clothing_suggester.util.Constants.CELSIUS_UNITS
import com.fofa.clothing_suggester.util.Constants.FIELDS
import com.fofa.clothing_suggester.util.Constants.FIELDS_VALUES
import com.fofa.clothing_suggester.util.Constants.HTTPS_SCHEME
import com.fofa.clothing_suggester.util.Constants.LOCATION
import com.fofa.clothing_suggester.util.Constants.ONE_DAY_TIME_STEPS
import com.fofa.clothing_suggester.util.Constants.TIME_LINES
import com.fofa.clothing_suggester.util.Constants.TIME_STEPS
import com.fofa.clothing_suggester.util.Constants.UNITS
import com.fofa.clothing_suggester.util.NetworkUtils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class ApiClient(private val utils: NetworkUtils, latitude: String, longitude: String) {
    var intervals = listOf<Interval>()

    private val client: OkHttpClient by lazy {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    val url = HttpUrl.Builder()
        .scheme(HTTPS_SCHEME)
        .host(BASE_URL)
        .addPathSegment("v4")
        .addPathSegment(TIME_LINES)
        .addQueryParameter(LOCATION, "$latitude,$longitude")
        .addQueryParameter(FIELDS, FIELDS_VALUES)
        .addQueryParameter(TIME_STEPS, ONE_DAY_TIME_STEPS)
        .addQueryParameter(UNITS, CELSIUS_UNITS)
        .addQueryParameter(APIKEY, APIKEY)
        .build()


    fun makeRequest(callback: (List<Interval>?, String?) -> Unit) {
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null, e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val jsonArray = utils.getIntervalsJsonArrayFromJson(jsonString!!)
                    intervals = utils.parseIntervals(jsonArray)
                    callback(intervals, null)
                }
            }

        })
    }


}