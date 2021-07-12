package com.criminal_code.test_mvp.model.current.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("population")
    @Expose
    var population: Long = 0

    @SerializedName("timezone")
    @Expose
    var timezone: Long = 0

    @SerializedName("sunrise")
    @Expose
    var sunrise: Long = 0

    @SerializedName("sunset")
    @Expose
    var sunset: Long = 0

}