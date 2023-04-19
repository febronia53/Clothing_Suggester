package com.fofa.clothing_suggester.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {
    private var prefrence: SharedPreferences? = null
    private const val PREF_NAME = "my_preferences"
    private const val START_TIME_KEY = "start_time"
    private const val CLOTHES_IMAGE_ID_KEY = "clothes_image_id"

    fun initPrefUtils(context: Context){
        prefrence = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    }

    var startTime: String?
        get() = prefrence?.getString(START_TIME_KEY, null)
        set(value) {
            prefrence?.edit()?.putString(START_TIME_KEY,value)?.apply()
        }
    var imageId: String?
        get() = prefrence?.getString(CLOTHES_IMAGE_ID_KEY, null)
        set(value) {
            prefrence?.edit()?.putString(CLOTHES_IMAGE_ID_KEY,value)?.apply()
        }
}