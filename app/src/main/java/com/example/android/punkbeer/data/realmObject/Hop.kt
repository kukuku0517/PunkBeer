package com.example.android.punkbeer.data.realmObject

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Hop: RealmObject(){

        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("amount")
        @Expose
        var amount: Amount? = null
        @SerializedName("add")
        @Expose
        var add: String? = null
        @SerializedName("attribute")
        @Expose
        var attribute: String? = null
}
