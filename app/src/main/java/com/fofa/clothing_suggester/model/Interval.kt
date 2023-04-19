package com.fofa.clothing_suggester.model

import androidx.annotation.DrawableRes


class Interval(
    val startTime: String,
    val values: Values,
    val weatherType: DayWeatherType,
    @DrawableRes var clothesImageId: Int,
)