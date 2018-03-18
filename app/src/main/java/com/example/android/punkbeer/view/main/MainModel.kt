package com.example.android.punkbeer.view.main

import com.example.android.punkbeer.data.POJO.BeerPOJO
import com.example.android.punkbeer.data.RealmRepository
import com.example.android.punkbeer.data.realmObject.Beer
import com.example.android.punkbeer.retrofit.PunkRepository
import com.example.android.punkbeer.retrofit.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable


/**
 * Created by samsung on 2018-03-11.
 */

class MainModel(var punkRepository: PunkRepository, var realmRepository: Repository) : MainMVP.Model {
    val STALE_MS = 20 * 1000
    val perPage = 25
    var timeStamp = 0L

    fun isUpToDate(): Boolean {
        val now = System.currentTimeMillis()
        return if (timeStamp == 0L || now - timeStamp > STALE_MS) {
            timeStamp = now
            true
        } else {
            false
        }
    }

    override fun findWithoutQuery(page: Int, isConnected: Boolean): Observable<BeerPOJO> {
        return if (!isConnected) {
            findWithoutQueryFromLocal(page)
        } else if ((isConnected && !isUpToDate())) {
            findWithoutQueryFromLocal(page).switchIfEmpty(findWithoutQueryFromServer(page))
        } else {
            findWithoutQueryFromServer(page)
        }
    }

    override fun <T> findWithQuery(page: Int, queryMap: MutableMap<String, T>, isConnected: Boolean): Observable<BeerPOJO> {
        return if (!isConnected || (isConnected && !isUpToDate())) {
            findWithQueryFromLocal(page, queryMap)
        } else if ((isConnected && !isUpToDate())) {
            findWithQueryFromLocal(page, queryMap).switchIfEmpty(findWithQueryFromServer(page, queryMap))
        } else {
            findWithQueryFromServer(page, queryMap)
        }
    }

    override fun findWithoutQueryFromServer(page: Int): Observable<BeerPOJO> {
        return punkRepository.getWithoutQuery(page, perPage)
                .flatMap { it.toObservable() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach {
                    (realmRepository as RealmRepository).createRealmFromPOJO(it.value)
                    it
                }

    }

    override fun <T> findWithQueryFromServer(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO> {
        return punkRepository.getWithQuery(page, perPage, queryMap).flatMap { it.toObservable() }
    }

    override fun findWithoutQueryFromLocal(page: Int): Observable<BeerPOJO> {
        return realmRepository.getWithoutQuery(page)
    }

    override fun <T> findWithQueryFromLocal(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO> {
        return realmRepository.getWithQuery(page, queryMap)
    }


}