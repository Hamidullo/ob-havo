package com.criminal_code.test_mvp.model.item

import android.location.Location
import com.criminal_code.test_mvp.model.current.IWeatherCurrentService
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import javax.inject.Inject

class ItemWeather @Inject constructor(): IItemWeather {
    override fun getWeatherItem(city: String): Observable<ItemWeatherData?>? {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org/")
                .build()

        val weatherService = retrofit.create(IItemWeatherService::class.java)

        return weatherService.weatherData(city,"metric","87371da9baf6be746254d014e4eac004")
    }

    override fun getWeatherItemByLocation(location: Location): Observable<ItemWeatherData?>? {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org/")
                .build()

        val weatherService = retrofit.create(IItemWeatherService::class.java)

        return weatherService.weatherDataByLocation(location.latitude.toString(),location.longitude.toString(),"metric","87371da9baf6be746254d014e4eac004")
    }
}