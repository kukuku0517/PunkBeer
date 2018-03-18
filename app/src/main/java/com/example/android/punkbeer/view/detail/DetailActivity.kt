package com.example.android.punkbeer.view.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.punkbeer.R
import com.example.android.punkbeer.data.RealmRepository
import com.example.android.punkbeer.data.realmObject.Beer
import com.example.android.punkbeer.view.DetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra("id", 0)
        if (id != 0) {
            init(id)
        }
    }

    fun init(id: Int) {
        val beer = RealmRepository().findDataByProperty(Beer::class.java, "id", id)
        beer?.let {
            text_detail_name.setText(beer.name)
            text_detail_tagline.setText(beer.tagline)
            text_detail_firstBrewed.setText(beer.first_brewed)
            text_detail_description.setText(beer.description)
            Glide.with(this).load(beer.image_url).into(text_detail_image)
            text_detail_abv.setText(beer.abv.toString())
            text_detail_ibu.setText(beer.ibu.toString())
            text_detail_ebc.setText(beer.ebc.toString())

            val ingredientAdapter = DetailAdapter<Any>()
            val ingredientList = mutableListOf<Any>()
            beer.ingredients?.malt?.mapTo(ingredientList) { it }
            beer.ingredients?.hops?.mapTo(ingredientList) { it }

            recycler_ingredient.adapter = ingredientAdapter
            recycler_ingredient.layoutManager = LinearLayoutManager(this)
            ingredientAdapter.updateData(ingredientList)

            val paringAdapter = DetailAdapter<Any>()
            val pairingList = mutableListOf<Any>()
            beer.food_pairing?.mapTo(pairingList) { it }
            recycler_foodPairing.adapter = paringAdapter
            recycler_foodPairing.layoutManager = LinearLayoutManager(this)
            text_detail_brewerTips.setText(beer.brewers_tips)
            paringAdapter.updateData(pairingList)
        }
    }
}
