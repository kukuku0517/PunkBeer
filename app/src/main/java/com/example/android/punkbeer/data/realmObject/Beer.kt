package com.example.android.punkbeer.data.realmObject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Beer:RealmObject(){

        var a:Int?=null

        @SerializedName("id")
        @Expose
        @PrimaryKey
        var id: Int? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("tagline")
        @Expose
        var tagline: String? = null
        @SerializedName("first_brewed")
        @Expose
        var first_brewed: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("image_url")
        @Expose
        var image_url: String? = null
        @SerializedName("abv")
        @Expose
        var abv: Double? = null
        @SerializedName("ibu")
        @Expose
        var ibu: Float? = null
        @SerializedName("target_fg")
        @Expose
        var target_fg: Float? = null
        @SerializedName("target_og")
        @Expose
        var target_og: Float? = null
        @SerializedName("ebc")
        @Expose
        var ebc: Float? = null
        @SerializedName("srm")
        @Expose
        var srm: Float? = null
        @SerializedName("ph")
        @Expose
        var ph: Double? = null
        @SerializedName("attenuation_level")
        @Expose
        var attenuation_level: Float? = null
        @SerializedName("volume")
        @Expose
        var volume: Volume? = null
        @SerializedName("boil_volume")
        @Expose
        var boil_volume: BoilVolume? = null
        @SerializedName("method")
        @Expose
        var method: Method? = null
        @SerializedName("ingredients")
        @Expose
        var ingredients: Ingredients? = null
        @SerializedName("food_pairing")
        @Expose
        var food_pairing: RealmList<StringRealm>? = null
        @SerializedName("brewers_tips")
        @Expose
        var brewers_tips: String? = null
        @SerializedName("contributed_by")
        @Expose
        var contributed_by: String? = null
}
