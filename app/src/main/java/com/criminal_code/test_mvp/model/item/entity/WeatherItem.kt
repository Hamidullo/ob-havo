package com.criminal_code.test_mvp.model.item.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherItem {
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null
}