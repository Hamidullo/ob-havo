package com.criminal_code.test_mvp.model.item.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoordItem {

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null


}