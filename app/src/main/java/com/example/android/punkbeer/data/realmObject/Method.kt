package com.example.android.punkbeer.data.realmObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Method: RealmObject() {

    @SerializedName("mash_temp")
    @Expose
    var mash_temp: RealmList<MashTemp>? = null
    @SerializedName("fermentation")
    @Expose
    var fermentation: Fermentation? = null
    @SerializedName("twist")
    @Expose
    var twist: String? = null
}