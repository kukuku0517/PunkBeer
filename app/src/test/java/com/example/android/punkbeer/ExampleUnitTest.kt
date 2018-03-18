package com.example.android.punkbeer

import com.example.android.punkbeer.data.POJO.AmountPOJO
import com.google.gson.Gson
import io.realm.Realm
import org.junit.Test

import org.junit.Assert.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun POJOtoRealmObject(){
        val amountPOJO = AmountPOJO(0.0,"hello")
        val json = Gson().toJson(amountPOJO)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.createAllFromJson(com.example.android.punkbeer.data.realmObject.Amount::class.java,json)
        realm.commitTransaction()
        assertEquals("hello",realm.where(com.example.android.punkbeer.data.realmObject.Amount::class.java).findFirst()?.value)
    }

}
