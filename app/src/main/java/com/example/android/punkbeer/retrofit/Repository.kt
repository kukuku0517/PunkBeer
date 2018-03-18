package com.example.android.punkbeer.retrofit

import com.example.android.punkbeer.data.POJO.BeerPOJO


/**
 * Created by samsung on 2018-03-11.
 */
interface Repository {
    fun getWithoutQuery(page: Int): io.reactivex.Observable<BeerPOJO>
    fun <T> getWithQuery(page: Int, queryMap: MutableMap<String, T>): io.reactivex.Observable<BeerPOJO>
}