package com.criminal_code.test_mvp.model.current

import android.location.Location
import com.criminal_code.test_mvp.model.current.entity.WeatherCurrentData
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData

interface IWeatherCurrent {
    fun getWeatherCurrent(city: String = "Moscow"): rx.Observable<WeatherCurrentData?>?

}