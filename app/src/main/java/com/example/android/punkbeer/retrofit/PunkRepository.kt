package com.example.android.punkbeer.retrofit

import com.example.android.punkbeer.data.POJO.BeerPOJO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by samsung on 2018-02-25.
 */
interface PunkRepository {

    @GET("beers")
    fun <T> getWithQuery(@Query("page") page: Int,
                     @Query("per_page") perPage: Int,
                     @QueryMap query: MutableMap<String, T>)
            : Observable<MutableList<BeerPOJO>>

    @GET("beers")
    fun getWithoutQuery(@Query("page") page: Int,
                        @Query("per_page") perPage: Int)
            : Observable<MutableList<BeerPOJO>>


}