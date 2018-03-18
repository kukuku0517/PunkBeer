package com.example.android.punkbeer.data.POJO

import com.example.android.punkbeer.data.realmObject.Fermentation
import com.example.android.punkbeer.data.realmObject.MashTemp
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MethodPOJO(

        @SerializedName("mash_temp")
    @Expose
    var mash_temp: List<MashTemp>? = null,
        @SerializedName("fermentation")
    @Expose
    var fermentation: Fermentation? = null,
        @SerializedName("twist")
    @Expose
    var twist: Any? = null
    )