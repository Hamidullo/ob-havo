package com.criminal_code.test_mvp.model.current.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListWeather {

    @SerializedName("dt")
    @Expose
    var dt: Long = 0

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather> = ArrayList()

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Long = 0

    @SerializedName("pop")
    @Expose
    var pop: Double? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("dt_txt")
    @Expose
    var dt_txt: String? = null
}