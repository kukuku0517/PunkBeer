package com.example.android.punkbeer.view.main

import com.example.android.punkbeer.data.POJO.BeerPOJO
import io.reactivex.Observable

/**
 * Created by samsung on 2018-03-10.
 */
class MainMVP {
    interface View {
        fun initData(data: MutableList<BeerPOJO>)
        fun updateData(data: MutableList<BeerPOJO>)
    }

    interface Presenter {
        fun setView(view:View)
        fun <T> initData(queryMap: MutableMap<String, T>)
        fun <T> updateData(page: Int, queryMap: MutableMap<String, T>)
        fun findWithoutQuery(page: Int): Observable<BeerPOJO>
        fun <T> findWithQuery(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO>
    }

    interface Model {
        fun findWithoutQuery(page: Int, isConnected:Boolean): Observable<BeerPOJO>
        fun <T> findWithQuery(page: Int, queryMap: MutableMap<String, T>, isConnected:Boolean): Observable<BeerPOJO>

        fun findWithoutQueryFromServer(page: Int): Observable<BeerPOJO>
        fun <T> findWithQueryFromServer(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO>
        fun findWithoutQueryFromLocal(page: Int): Observable<BeerPOJO>
        fun <T> findWithQueryFromLocal(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO>

    }
}