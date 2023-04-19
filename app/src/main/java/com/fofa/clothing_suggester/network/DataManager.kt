package com.fofa.clothing_suggester.network

import com.fofa.clothing_suggester.R
import com.fofa.clothing_suggester.model.DayWeatherType
import com.fofa.clothing_suggester.model.Values

class DataManager {
    private fun getClothesList(dayWeatherType: DayWeatherType): List<Int> {
        return when (dayWeatherType) {
            DayWeatherType.WINTER -> {
                listOf(
                    R.drawable.winter1,
                    R.drawable.winter2,
                    R.drawable.winter3,
                    R.drawable.winter4,
                    R.drawable.winter5,
                    R.drawable.winter6
                )
            }
            DayWeatherType.SUMMER -> {
                listOf(
                    R.drawable.summer1,
                    R.drawable.summer2,
                    R.drawable.summer3,
                    R.drawable.summer4,
                    R.drawable.summer5,
                    R.drawable.summer6
                )
            }
            DayWeatherType.SPRING -> {
                listOf(
                    R.drawable.spring1,
                    R.drawable.spring2,
                    R.drawable.spring3,
                    R.drawable.spring4,
                    R.drawable.spring5,
                    R.drawable.spring6
                )
            }
            else -> {
                listOf(
                    R.drawable.autumn1,
                    R.drawable.autumn2,
                    R.drawable.autumn3,
                    R.drawable.autumn4,
                    R.drawable.autumn5,
                    R.drawable.autumn6
                )
            }
        }
    }

    fun getClothesImageId(dayWeatherType: DayWeatherType): Int {
        return when (dayWeatherType) {
            DayWeatherType.WINTER -> {
                getClothesList(dayWeatherType).random()
            }
            DayWeatherType.SUMMER -> {
                getClothesList(dayWeatherType).random()
            }
            DayWeatherType.SPRING -> {
                getClothesList(dayWeatherType).random()
            }
            else -> {
                getClothesList(dayWeatherType).random()
            }
        }
    }


    fun getClothesImageId(dayWeatherType: DayWeatherType, clothesImage: Int) =
        (getClothesList(dayWeatherType) - clothesImage).random()


    fun getDatWeatherType(valves: Values): DayWeatherType {
        return when {
            valves.temperature < 15.0 -> {
                DayWeatherType.WINTER
            }
            valves.temperature in 15.0..25.0 -> {
                DayWeatherType.SPRING
            }
            valves.temperature in 25.0..32.0 -> {
                DayWeatherType.FALL
            }
            else -> {
                DayWeatherType.SUMMER
            }
        }
    }
}
