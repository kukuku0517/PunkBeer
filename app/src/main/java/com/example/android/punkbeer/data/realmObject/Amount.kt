package com.example.android.punkbeer.data.realmObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Amount:RealmObject(){
        @SerializedName("value")
        @Expose
        var value: Double? = null
        @SerializedName("unit")
        @Expose
        var unit: String? = null
}