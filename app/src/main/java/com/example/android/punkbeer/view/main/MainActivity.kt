package com.example.android.punkbeer.view.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.android.punkbeer.R
import com.example.android.punkbeer.view.main.viewholder.BeerListAdapter
import com.example.android.punkbeer.data.POJO.BeerPOJO
import com.example.android.punkbeer.root.App
import com.example.android.punkbeer.view.detail.DetailActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_beer.view.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainMVP.View {
    override fun initData(data: MutableList<BeerPOJO>) {
        refresh_main.isRefreshing = false
        adapter.resetData(data)
    }

    override fun updateData(data: MutableList<BeerPOJO>) {
        refresh_main.isRefreshing = false
        adapter.updateData(data)
    }

    lateinit var adapter: BeerListAdapter<BeerPOJO>
    lateinit var layoutManager: LinearLayoutManager
    val PER_PAGE = 25
    var currentPage = 1
    val QUERY = arrayOf("Name", "ABV")


    @Inject
    lateinit var presenter: MainMVP.Presenter


    var queryFlag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.inject(this)

        /**
         * Realm 초기화
         * **/
        Realm.init(this)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build())

        /**
         * view 초기화
         */
        refresh_main.setOnRefreshListener {
            refreshData()
        }

        btn_main_query.setOnClickListener {
            queryFlag = (queryFlag + 1) % QUERY.size
            btn_main_query.setText(QUERY[queryFlag])
        }

        search_main.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(s: String): Boolean {
//                updateDataOnQuery(1, s)
                val queryMap = mutableMapOf<String, String>()
                queryMap.put(QUERY[queryFlag], s)
                presenter.initData(queryMap)
                return false
            }


            override fun onQueryTextChange(s: String): Boolean {

                return false
            }
        })



        adapter = BeerListAdapter(
                R.layout.item_beer,
                /**
                 * Glide를 통해 이미지를 캐싱함
                 */
                { holder, data ->
                    holder?.itemView?.text_item_id?.setText(data.id.toString())
                    holder?.itemView?.text_item_name?.setText(data.name)
                    holder?.itemView?.text_item_abv?.setText("${data.abv.toString()}%")
                    holder?.itemView?.text_item_tagline?.setText(data.tagline)
                    Glide.with(this)
                            .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.beer).diskCacheStrategy(DiskCacheStrategy.ALL))
                            .load(data.image_url)
                            .into(holder?.itemView?.text_item_image)
                    holder?.itemView?.setOnClickListener {
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("id", data.id)
                        startActivity(intent)
                    }
                },
                /**
                 * Scroll bottom callback
                 * recyclerView의 하단에 도달했을 경우
                 * 다음 페이지의 아이템을 불러온다
                 */
                { position ->
                    val page = position / PER_PAGE
                    updateDataOnScroll(page + 1)

                })
        layoutManager = LinearLayoutManager(this)
        adapter.hasStableIds()
        recycler_main.adapter = adapter
        recycler_main.layoutManager = layoutManager

    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
        presenter.initData(getQueryMap())
    }


    fun refreshData() {
        refresh_main.isRefreshing = true
        search_main.setQuery("", false)
        currentPage = 1
//        if (checkInternetConnection()) {
//            val retrofit = RetrofitRepository()
////            retrofit.getDataByPage(1, PER_PAGE) { body ->
////                refresh_main.isRefreshing = false
////                adapter.updateData(body)
////                RealmRepository(this).createRealmFromPOJOs(Beer::class.java, body)
////            }
//        } else {
//            Handler().post({
//                RealmRepository(this).getBeersByPageFromRealm(Beer::class.java, BeerPOJO::class.java, 1, PER_PAGE) { data ->
//                    refresh_main.isRefreshing = false
//                    adapter.updateData(data)
//                }
//            })
//        }
        presenter.initData(getQueryMap())
    }

    /**
     * @param page : 페이지 번호
     * 새로운 page에 해당하는 데이터를 가져온 후 기존 데이터와 병합한다
     */
    fun updateDataOnScroll(page: Int) {
        Log.d("kjh","$currentPage&$page")
        if (currentPage < page) {
            currentPage = page
            refresh_main.isRefreshing = true
            presenter.updateData(page, getQueryMap())
        }
    }

    fun getQueryMap(): MutableMap<String, String> {
        return mutableMapOf<String, String>()
    }

//    private fun updateDataOnQuery(page: Int, s: String) {
////        RealmRepository(applicationContext).findDatasByProperty(queryFlag, s, page, PER_PAGE) { result ->
////            refresh_main.isRefreshing = false
////            adapter.addData(result)
////        }
//    }
//
//    /**
//     * @return  인터넷 연결상태
//     */
//    fun checkInternetConnection(): Boolean {
//        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = cm.activeNetworkInfo
//        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
//    }
}
