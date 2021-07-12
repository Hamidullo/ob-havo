package com.criminal_code.test_mvp.model.current.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {

    @SerializedName("speed")
    @Expose
    var speed: Double? = null

    @SerializedName("deg")
    @Expose
    var deg: Long = 0

    @SerializedName("gust")
    @Expose
    var gust: Double? = null
}