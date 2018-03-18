package com.example.android.punkbeer.view.detail.viewholder

import android.view.View
import com.example.android.punkbeer.data.realmObject.StringRealm
import com.example.android.punkbeer.view.DetailBaseViewHolder
import kotlinx.android.synthetic.main.item_food.view.*

/**
 * Created by samsung on 2018-02-26.
 */
class FoodViewHolder<T>(itemView: View?) : DetailBaseViewHolder<T>(itemView) {
    override fun bindView(data: T) {

        itemView.text_food_description.text = (data as StringRealm).value


    }


}