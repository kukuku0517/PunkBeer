package com.example.android.punkbeer.data.realmObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class MashTemp : RealmObject(){

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null
    @SerializedName("duration")
    @Expose
    var duration: Int? = null

}
