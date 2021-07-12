package com.criminal_code.test_mvp.model.item.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemWeatherData {
    @SerializedName("coord")
    @Expose
    var coord: CoordItem? = null

    @SerializedName("weather")
    @Expose
    var weather: List<WeatherItem> = ArrayList()

    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("main")
    @Expose
    var main: MainItem? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Long = 0

    @SerializedName("wind")
    @Expose
    var wind: WindItem? = null

    @SerializedName("clouds")
    @Expose
    var clouds: CloudsItem? = null

    @SerializedName("dt")
    @Expose
    var dt: Long = 0

    @SerializedName("sys")
    @Expose
    var sys: SysItem? = null

    @SerializedName("timezone")
    @Expose
    var timezone: Long = 0

    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cod")
    @Expose
    var cod: Int? = null
}