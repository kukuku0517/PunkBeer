package com.example.android.punkbeer.data.POJO

import com.example.android.punkbeer.data.realmObject.Hop
import com.example.android.punkbeer.data.realmObject.Malt
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class IngredientsPOJO {

    @SerializedName("malt")
    @Expose
    var malt: List<Malt>? = null
    @SerializedName("hops")
    @Expose
    var hops: List<Hop>? = null
    @SerializedName("yeast")
    @Expose
    var yeast: String? = null

}
