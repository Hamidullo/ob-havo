package com.criminal_code.test_mvp.model.item.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WindItem {
    @SerializedName("speed")
    @Expose
    var speed: Double? = null

    @SerializedName("deg")
    @Expose
    var deg: Long = 0
}