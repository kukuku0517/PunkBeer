package com.example.android.punkbeer.data.POJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TempPOJO {

    @SerializedName("value")
    @Expose
    var value: Int? = null
    @SerializedName("unit")
    @Expose
    var unit: String? = null

}
