package com.example.android.punkbeer.view.detail.viewholder

import android.view.View
import com.example.android.punkbeer.data.realmObject.Hop
import com.example.android.punkbeer.view.DetailBaseViewHolder
import kotlinx.android.synthetic.main.item_hop.view.*

/**
 * Created by samsung on 2018-02-25.
 */
class HopViewHolder<T>(itemView: View?) : DetailBaseViewHolder<T>(itemView) {
    override fun bindView(data: T) {

        itemView.text_hop_name.text = (data as Hop).name
        itemView.text_hop_amount.text = "${(data as Hop).amount?.value} ${(data as Hop).amount?.unit}"
        itemView.text_hop_attribute.text = (data as Hop).attribute

    }


}