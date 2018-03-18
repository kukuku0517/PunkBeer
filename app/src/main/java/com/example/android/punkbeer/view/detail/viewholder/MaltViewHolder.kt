package com.example.android.punkbeer.view.detail.viewholder

import android.view.View
import com.example.android.punkbeer.data.realmObject.Malt
import com.example.android.punkbeer.view.DetailBaseViewHolder
import kotlinx.android.synthetic.main.item_malt.view.*

/**
 * Created by samsung on 2018-02-25.
 */
class MaltViewHolder<T>(itemView: View?) : DetailBaseViewHolder<T>(itemView) {
    override fun bindView(data:T) {

        itemView.text_malt_name.text = (data as Malt).name
        itemView.text_malt_amount.text = "${(data as Malt).amount?.value} ${(data as Malt).amount?.unit}"


    }
}