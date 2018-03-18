package com.example.android.punkbeer.view

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by samsung on 2018-02-25.
 */
abstract class DetailBaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindView(data: T)
}