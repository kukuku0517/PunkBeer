package com.example.android.punkbeer.data.POJO

import com.example.android.punkbeer.data.realmObject.Amount
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HopPOJO(

        @SerializedName("name")
    @Expose
    var name: String? = null,
        @SerializedName("amount")
    @Expose
    var amount: Amount? = null,
        @SerializedName("add")
    @Expose
    var add: String? = null,
        @SerializedName("attribute")
    @Expose
    var attribute: String? = null
)
