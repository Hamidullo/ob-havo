package com.criminal_code.test_mvp.model.item.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CloudsItem {
    @SerializedName("all")
    @Expose
    var all: Long = 0
}