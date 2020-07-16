package com.harrycampaz.carshop.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.data.category.Categories
import com.harrycampaz.carshop.model.Car
import com.harrycampaz.carshop.model.CarWithCategory
import com.harrycampaz.carshop.ui.listener.OnItemCarListener
import kotlinx.android.synthetic.main.item_car.view.*

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(carWithCategory: CarWithCategory, onItemCarListener: OnItemCarListener) {

        carWithCategory?.apply {
            itemView.iv_item_car.setImageResource(R.drawable.ic_baseline_car_commercial)
            itemView.tv_item_car_name.text = car?.model
            itemView.tv_item_car_category.text = category?.name
            itemView.tv_item_car_precio.text = car?.price.toString()
            itemView.tv_item_car_num_seat.text = car?.seat.toString()
            itemView.tv_item_car_date_release.text = car?.dateRelease.toString()

            car?.let {
                showSpecifyProperty(it)
            }

            itemView.btn_edit.setOnClickListener {view->
                car?.let { carSend -> onItemCarListener.onItemCarClicked(carSend) }
            }
        }

    }


    private fun showSpecifyProperty(car: Car){
        when(car.categoryId){
            Categories.ELECTRIC.id -> {
                itemView.tv_item_car_specify_name.text = "Capacidad de \nBateria:"
                itemView.tv_item_car_specify_value.text = "${car.batteryCapacity} w"
                itemView.btn_edit.visibility = View.INVISIBLE
            }
            Categories.TRUCK.id -> {
                itemView.tv_item_car_specify_name.text = "Max Carga:"
                itemView.tv_item_car_specify_value.text = "${car.maxPayload} Tm"
            }
            Categories.COMMERCIAL.id -> {
                itemView.tv_item_car_specify_name.text = "Espacio \ncapacidad: "
                itemView.tv_item_car_specify_value.text = "${car.spaceCapacity} m2"
            }
        }

    }


}
