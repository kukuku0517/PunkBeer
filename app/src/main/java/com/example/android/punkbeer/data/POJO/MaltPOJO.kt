package com.example.android.punkbeer.data.POJO

import com.example.android.punkbeer.data.realmObject.Amount
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MaltPOJO(

    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("amount")
    @Expose
    var amount: Amount? = null
    )
