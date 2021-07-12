package com.criminal_code.test_mvp.model.item

import android.location.Location
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData

interface IItemWeather {
    fun getWeatherItem(city: String = "Tashkent"): rx.Observable<ItemWeatherData?>?
    fun getWeatherItemByLocation(location: Location): rx.Observable<ItemWeatherData?>?
}