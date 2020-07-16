package com.harrycampaz.carshop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.model.CarWithCategory
import kotlinx.android.synthetic.main.item_car.view.*

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(carWithCategory: CarWithCategory) {

        carWithCategory?.apply {
            itemView.iv_item_car.setImageResource(R.drawable.ic_baseline_car_commercial)
            itemView.tv_item_car_name.text = car?.model
            itemView.tv_item_car_category.text = category?.name
            itemView.tv_item_car_precio.text = car?.price.toString()
            itemView.tv_item_car_num_seat.text = car?.seat.toString()
        }



    }


}
