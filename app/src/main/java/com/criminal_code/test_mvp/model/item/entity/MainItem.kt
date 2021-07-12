package com.criminal_code.test_mvp.model.item.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainItem {

    @SerializedName("temp")
    @Expose
    var temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    var feels_like: Double? = null

    @SerializedName("temp_min")
    @Expose
    var temp_min: Double? = null

    @SerializedName("temp_max")
    @Expose
    var temp_max: Double? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Long = 0

    @SerializedName("humidity")
    @Expose
    var humidity: Int = 0
}