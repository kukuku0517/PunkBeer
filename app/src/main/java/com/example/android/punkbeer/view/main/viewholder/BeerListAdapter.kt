package com.example.android.punkbeer.view.main.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by samsung on 2018-02-25.
 */
class BeerListAdapter<T>(resource: Int,
                         val holderCallback: (holder: BeerListViewHolder<T>?, data: T) -> Unit,
                         val scrollCallback: (position:Int) -> Unit) : RecyclerView.Adapter<BeerListViewHolder<T>>() {

    var data: MutableList<T>?
    val resource = resource

    init {
        this.data = mutableListOf()
    }

    fun updateData(data: MutableList<T>?) {
        data?.forEach {
            this.data?.add(it)
        }
        notifyDataSetChanged()
    }

    fun resetData(data:MutableList<T>) {
        this.data=data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeerListViewHolder<T> =
            BeerListViewHolder(LayoutInflater.from(parent?.context).inflate(resource, parent, false))


    override fun onBindViewHolder(holder: BeerListViewHolder<T>?, position: Int) {
        if(position==itemCount-1){
            scrollCallback(itemCount)
        }
        data?.get(position)?.let { holderCallback.invoke(holder, it) }
    }

    override fun getItemCount(): Int {
        return if (data != null) {
            data!!.size
        } else {
            0
        }
    }


}