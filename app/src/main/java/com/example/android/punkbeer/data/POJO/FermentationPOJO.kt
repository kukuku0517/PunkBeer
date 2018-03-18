package com.example.android.punkbeer.data.POJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FermentationPOJO(

    @SerializedName("temp")
    @Expose
    var temp: TempPOJO? = null
    )
