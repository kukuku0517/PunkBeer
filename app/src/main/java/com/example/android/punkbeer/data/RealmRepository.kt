package com.example.android.punkbeer.data

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.MainThread
import android.util.Log
import com.example.android.punkbeer.data.POJO.BeerPOJO
import com.example.android.punkbeer.data.realmObject.Beer
import com.example.android.punkbeer.data.realmObject.StringRealm
import com.example.android.punkbeer.retrofit.Repository
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import com.google.gson.TypeAdapter
import com.google.gson.FieldAttributes
import com.google.gson.ExclusionStrategy
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers


/**
 * Created by samsung on 2018-02-25.
 */
class RealmRepository : Repository {
    var gson: Gson

    init {
        val token = object : TypeToken<RealmList<StringRealm>>() {}.type
        /**
         * [{String},{String},{String}] <-> RealmList<StringRealm> 의 변환
         * primitive type의 array와 JsonObject의 List간의 변환 로직을 Gson TypeAdapter를 통해 직접 구현해야함
         */
        gson = GsonBuilder()
                .setExclusionStrategies(object : ExclusionStrategy {
                    override fun shouldSkipField(f: FieldAttributes) = f.getDeclaringClass().equals(RealmObject::class.java)

                    override fun shouldSkipClass(clazz: Class<*>?) = false
                })
                .registerTypeAdapter(token, object : TypeAdapter<RealmList<StringRealm>>() {
                    override fun write(out: JsonWriter, value: RealmList<StringRealm>) {
                        out.beginArray()
                        value.forEach {
                            out.value(it.value)
                        }
                        out.endArray()
                    }

                    override fun read(input: JsonReader): RealmList<StringRealm> {
                        val list = RealmList<StringRealm>()
                        input.beginArray()
                        while (input.hasNext()) {
                            val stringRealm = StringRealm()
                            stringRealm.value = input.nextString()
                            list.add(stringRealm)
                        }
                        input.endArray()
                        return list
                    }
                })
                .create()


    }

    /**
     * data를 Json형식으로 변환한다
     *
     * @param data : Json으로 변환할 data
     * @return Json String
     */
    fun createJSONFromObject(data: Any): String? {
        if (data is RealmObject) {
            val realm = Realm.getDefaultInstance()
            return gson.toJson(realm.copyFromRealm(data))
        }
        return gson.toJson(data)
    }

    fun createRealmFromPOJO(data: BeerPOJO?) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync({ realm ->
            realm.copyToRealmOrUpdate(gson.fromJson<Beer>(data?.let { it1 -> createJSONFromObject(it1) }, Beer::class.java))
        }, { -> Log.d("kjh", "success") }, { e -> Log.d("kjh", e.message) })
    }
//    fun <T : RealmObject> createRealmFromPOJO(clazz: Class<T>, data: Any) {
//        val realm = Realm.getDefaultInstance()
//        realm.executeTransactionAsync({
//            realm.copyToRealmOrUpdate(gson.fromJson<Beer>(createJSONFromObject(data), clazz))
//        }, { -> Log.d("kjh", "success") }, { e -> Log.d("kjh", e.message) })
//    }


    /**
     * POJO객체들을 realm으로 변환하여 저장한다
     *
     * @param clazz : 변환 후 data 형식
     * @return : 변환한 realm data를 저장
     */
    fun <T : RealmObject> createRealmFromPOJOs(clazz: Class<T>, data: MutableList<BeerPOJO>?) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { realm ->
            data?.forEach {
                val temp = gson.fromJson<Beer>(createJSONFromObject(it), clazz)
                realm.insertOrUpdate(temp)
            }
        }
    }

    /**
     * realmObject를 POJO class로 변환한다
     *
     * @param data : 변환할 realmObject
     * @return : 변환된 POJO객체
     */
    fun <T> createPOJOFromRealm(clazz: Class<T>, data: RealmObject): T {
        return gson.fromJson(createJSONFromObject(data), clazz)
    }

    fun <T : RealmObject> findDataByProperty(clazz: Class<T>, property: String, value: String): T? {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val beer = realm.where(clazz).equalTo(property, value).findFirst()
        realm.commitTransaction()
        beer?.let { return createPOJOFromRealm(clazz, it) }
        return null
    }

    /**
     * @param property : query할 property 이름
     * @param value : property의 값
     * @return query결과
     */
    fun <T : RealmObject> findDataByProperty(clazz: Class<T>, property: String, value: Int): T? {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val beer = realm.where(clazz).equalTo(property, value).findFirst()
        realm.commitTransaction()
        beer?.let { return createPOJOFromRealm(clazz, it) }
        return null
    }

    fun <T : RealmObject> findDatasByProperty(clazz: Class<T>, property: String, value: Int): MutableList<T> {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val beers = realm.where(clazz).equalTo(property, value).findAll()
        realm.commitTransaction()
        val result = mutableListOf<T>()
        beers.mapTo(result) { createPOJOFromRealm(clazz, it) }
        return result
    }

    val QUERY_SERVER = arrayOf("beer_name", "abv_gt")
    val QUERY_LOCAL = arrayOf("name", "abv")

//    fun findDatasByProperty(property: Int, value: String, page: Int, perPage: Int, listener: (result: MutableList<BeerPOJO>) -> Unit) {
//        val result = mutableListOf<BeerPOJO>()
//        if (checkInternetConnection()) {
//            when (property) {
//                0 -> RetrofitRepository().getDataByQuery(page, perPage, QUERY_SERVER[property], value) { result ->
//                    result?.let { listener.invoke(it) }
//                }
//                1 -> RetrofitRepository().getDataByQuery(page, perPage, QUERY_SERVER[property], value.toFloat()) { result ->
//                    result?.let { listener.invoke(it) }
//                }
//
//                else ->{}
//            }
//
//        } else {
//            val realm = Realm.getDefaultInstance()
//            realm.executeTransactionAsync({ realm ->
//                val beers = when (property) {
//                    0 -> realm.where(Beer::class.java).contains(QUERY_LOCAL[property], value).findAll()
//                    1 -> realm.where(Beer::class.java).greaterThan(QUERY_LOCAL[property], value.toDouble()).findAll()
//                    else -> realm.where(Beer::class.java).findAll()
//                }
//                beers.mapTo(result) { createPOJOFromRealm(BeerPOJO::class.java, it) }
//            }, { -> listener.invoke(result) })
//        }
//
//    }

    /**
     * 페이지에 해당하는 realm data를 callback을 통해 보냄
     */
    fun <T : RealmObject, G> getBeersByPageFromRealm(clazz: Class<T>, clazz2: Class<G>, page: Int, perPage: Int, listener: (data: MutableList<G>) -> Unit) {
        val realm = Realm.getDefaultInstance()
        val result = mutableListOf<G>()
        realm.executeTransactionAsync({ realm ->
            val startIndex = (page - 1) * perPage + 1
            val endIndex = (page) * perPage
            val beers = realm.where(clazz).between("id", startIndex, endIndex).findAll()
            beers.mapTo(result) { createPOJOFromRealm(clazz2, it) }
        }, { ->
            listener.invoke(result)
        })
    }

    val perPage = 25

    override fun getWithoutQuery(page: Int): Observable<BeerPOJO> {
        val realm = Realm.getDefaultInstance()
        val startIndex = (page - 1) * perPage + 1
        val endIndex = (page) * perPage
        val beersList = realm.where(Beer::class.java).between("id", startIndex, endIndex).findAll()
        return realm.copyFromRealm(beersList).toObservable()
                .map {
                    createPOJOFromRealm(BeerPOJO::class.java, it)
                }

    }

    override fun <T> getWithQuery(page: Int, queryMap: MutableMap<String, T>): Observable<BeerPOJO> {
        val realm = Realm.getDefaultInstance()
        val startIndex = (page - 1) * perPage + 1
        val endIndex = (page) * perPage
        val beersList = realm.where(Beer::class.java)
                .between("id", startIndex, endIndex)
                .findAll()
        return realm.copyFromRealm(beersList).toObservable()
                .map {
                    createPOJOFromRealm(BeerPOJO::class.java, it)
                }
    }


}