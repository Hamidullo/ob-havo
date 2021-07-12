package com.criminal_code.test_mvp.model.current

import com.criminal_code.test_mvp.model.current.entity.WeatherCurrentData
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface IWeatherCurrentService {

    @GET("data/2.5/forecast?") //q={city}/units=metric/appid=87371da9baf6be746254d014e4eac004
    fun weatherData(@Query("q") city: String,@Query("units") metric: String,@Query("appid") app_id: String): Observable<WeatherCurrentData?>?
}