package com.example.android.punkbeer.data.realmObject

import com.example.android.punkbeer.data.realmObject.Amount
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Malt : RealmObject() {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("amount")
    @Expose
    var amount: Amount? = null
}
