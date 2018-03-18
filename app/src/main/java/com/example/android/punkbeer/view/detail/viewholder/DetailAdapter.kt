package com.example.android.punkbeer.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.punkbeer.R
import com.example.android.punkbeer.data.realmObject.Hop
import com.example.android.punkbeer.data.realmObject.Malt
import com.example.android.punkbeer.data.realmObject.StringRealm
import com.example.android.punkbeer.view.detail.viewholder.FoodViewHolder
import com.example.android.punkbeer.view.detail.viewholder.HopViewHolder
import com.example.android.punkbeer.view.detail.viewholder.MaltViewHolder

/**
 * Created by samsung on 2018-02-25.
 */
class DetailAdapter<T>() : RecyclerView.Adapter<DetailBaseViewHolder<T>>() {

    var data: MutableList<T>?


    init {
        this.data = mutableListOf()
    }

    fun updateData(data: MutableList<T>?) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailBaseViewHolder<T> {
        return when (viewType) {
            1 -> HopViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_hop, parent, false))
            2 -> MaltViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_malt, parent, false))
            else -> FoodViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_food, parent, false))
        }

    }


    override fun onBindViewHolder(holder: DetailBaseViewHolder<T>?, position: Int) {
        data?.get(position)?.let { holder?.bindView(it) }
    }

    override fun getItemCount(): Int {
        return if (data != null) {
            data!!.size
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            data?.get(position) is Hop -> 1
            data?.get(position) is Malt -> 2
            data?.get(position) is StringRealm -> 3
            else -> 0
        }
    }
}