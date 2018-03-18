package com.example.android.punkbeer.data.POJO

import com.example.android.punkbeer.data.realmObject.Temp
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MashTempPOJO {

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null
    @SerializedName("duration")
    @Expose
    var duration: Int? = null

}
