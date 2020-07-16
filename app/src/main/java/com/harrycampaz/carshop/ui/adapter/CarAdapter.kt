package com.harrycampaz.carshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.model.CarWithCategory

class CarAdapter(var items: List<CarWithCategory>): RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        var carWithCategory = items[position]
        holder.bind(carWithCategory)
    }
}