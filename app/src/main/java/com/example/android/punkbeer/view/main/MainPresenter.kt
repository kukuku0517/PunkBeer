package com.example.android.punkbeer.view.main

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.punkbeer.data.POJO.BeerPOJO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by samsung on 2018-03-10.
 */
class MainPresenter(model: MainMVP.Model) : MainMVP.Presenter {

    private lateinit var view: MainMVP.View
    private lateinit var context: Context
    private val model: MainMVP.Model = model

    fun checkInternetConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun setView(view: MainMVP.View) {
        this.view = view
        this.context = view as Context
    }

    override fun <T> initData(queryMap: MutableMap<String, T>) {
        when (queryMap.isEmpty()) {
            true -> findWithoutQuery(1)
            false -> findWithQuery(1, queryMap)
        }.toList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data ->
                    view.initData(data)
                }
    }

    override fun <T> updateData(page: Int, queryMap: MutableMap<String, T>) {
        when (queryMap.isEmpty()) {
            true -> findWithoutQuery(page)
            false -> findWithQuery(page, queryMap)
        }.toList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data ->
                    view.updateData(data)
                }
    }

    override fun <T> findWithQuery(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO> {
        return model.findWithQuery(page, queryMap, checkInternetConnection())
    }


    override fun findWithoutQuery(page: Int): Observable<BeerPOJO> {
        return model.findWithoutQuery(page, checkInternetConnection())
    }

}