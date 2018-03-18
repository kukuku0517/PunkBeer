package com.example.android.punkbeer.data.realmObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Fermentation: RealmObject() {

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null
}