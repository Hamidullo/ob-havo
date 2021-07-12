package com.criminal_code.test_mvp.model.current

import android.location.Location
import com.criminal_code.test_mvp.model.current.entity.WeatherCurrentData
import com.criminal_code.test_mvp.model.item.IItemWeatherService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import javax.inject.Inject

class WeatherCurrent @Inject constructor() : IWeatherCurrent{
    override fun getWeatherCurrent(city: String): Observable<WeatherCurrentData?>? {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/")
            .build()

        val weatherService = retrofit.create(IWeatherCurrentService::class.java)

        return weatherService.weatherData(city,"metric","87371da9baf6be746254d014e4eac004")
    }

}