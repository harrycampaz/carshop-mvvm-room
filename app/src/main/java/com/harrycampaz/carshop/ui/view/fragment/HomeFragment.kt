package com.harrycampaz.carshop.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.harrycampaz.carshop.R
import com.harrycampaz.carshop.ui.adapter.CarAdapter
import com.harrycampaz.carshop.viewmodel.car.CarViewModel
import com.harrycampaz.carshop.viewmodel.car.CarViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


private const val TAG = "HomeFragment"
class HomeFragment : Fragment(),KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val factory: CarViewModelProviderFactory by instance<CarViewModelProviderFactory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, factory).get(CarViewModel::class.java)

        val adapter = CarAdapter(listOf())

        rv_car.layoutManager = GridLayoutManager(context, 2)
        rv_car.addItemDecoration(DividerItemDecoration(context, GridLayoutManager.VERTICAL))

        rv_car.adapter = adapter

        viewModel.getCarWithCategory().observe(viewLifecycleOwner, Observer { carWithCategory->

            Log.e(TAG, "onViewCreated: ${carWithCategory.size}")
            if(carWithCategory.isEmpty()){
               viewModel.isResultNotFound()
            }else {
               adapter.items = carWithCategory
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.resultNotFound.observe(viewLifecycleOwner, Observer {noResult ->
            if(noResult){
                ll_no_found.visibility = View.VISIBLE
            }else {
                ll_no_found.visibility = View.INVISIBLE
            }
        })

    }

}