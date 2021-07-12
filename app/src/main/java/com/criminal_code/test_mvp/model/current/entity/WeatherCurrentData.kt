package com.criminal_code.test_mvp.model.current.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

class WeatherCurrentData {

    @SerializedName("cod")
    @Expose
    var cod: Int? = null

    @SerializedName("message")
    @Expose
    var message: Int = 0

    @SerializedName("cnt")
    @Expose
    var cnt: Int = 0

    @SerializedName("list")
    @Expose
    var list: List<ListWeather> = ArrayList()

    @SerializedName("city")
    @Expose
    var city: City = City()

}