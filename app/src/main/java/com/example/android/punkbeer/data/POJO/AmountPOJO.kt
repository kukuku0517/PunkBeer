package com.example.android.punkbeer.data.POJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AmountPOJO(
        @SerializedName("value")
        @Expose
        var value: Double? = null,
        @SerializedName("unit")
        @Expose
        var unit: String? = null
)
