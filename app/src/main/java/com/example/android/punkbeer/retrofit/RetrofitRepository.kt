package com.example.android.punkbeer.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by samsung on 2018-02-25.
 */
class RetrofitRepository {
//    fun getDataByPage(page: Int, perPage: Int, listener: (MutableList<BeerPOJO>?) -> (Unit)) {
//        repository.getWithoutQuery(page, perPage).enqueue(object : Callback<MutableList<BeerPOJO>> {
//            override fun onResponse(call: Call<MutableList<BeerPOJO>>, response: Response<MutableList<BeerPOJO>>) {
//                listener.invoke(response.body())
//            }
//
//            override fun onFailure(call: Call<MutableList<BeerPOJO>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }
//
//    fun  getDataByQuery( page: Int, perPage: Int, query: String, value: String, listener: (result: MutableList<BeerPOJO>?) -> Unit) {
//        val map = mutableMapOf<String, String>()
//        map.put(query, value)
//        repository.getWithQuery(page, perPage, map).enqueue(object : Callback<MutableList<BeerPOJO>> {
//            override fun onResponse(call: Call<MutableList<BeerPOJO>>, response: Response<MutableList<BeerPOJO>>) {
//                listener.invoke(response.body())
//            }
//
//            override fun onFailure(call: Call<MutableList<BeerPOJO>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }fun  getDataByQuery( page: Int, perPage: Int, query: String, value: Float, listener: (result: MutableList<BeerPOJO>?) -> Unit) {
//        val map = mutableMapOf<String, Float>()
//        map.put(query, value)
//        repository.queryBeersByPage2(page, perPage, map).enqueue(object : Callback<MutableList<BeerPOJO>> {
//            override fun onResponse(call: Call<MutableList<BeerPOJO>>, response: Response<MutableList<BeerPOJO>>) {
//                listener.invoke(response.body())
//            }
//
//            override fun onFailure(call: Call<MutableList<BeerPOJO>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }

    val BASE_URL = "https://api.punkapi.com/v2/"
    var interceptor: HttpLoggingInterceptor
    var retrofit: Retrofit
    var repository: PunkRepository

    init {
        interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        repository = retrofit.create(PunkRepository::class.java)
    }


}