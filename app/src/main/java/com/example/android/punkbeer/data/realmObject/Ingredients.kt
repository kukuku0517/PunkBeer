package com.example.android.punkbeer.data.realmObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Ingredients : RealmObject(){

    @SerializedName("malt")
    @Expose
    var malt: RealmList<Malt>? = null
    @SerializedName("hops")
    @Expose
    var hops: RealmList<Hop>? = null
    @SerializedName("yeast")
    @Expose
    var yeast: String? = null

}
