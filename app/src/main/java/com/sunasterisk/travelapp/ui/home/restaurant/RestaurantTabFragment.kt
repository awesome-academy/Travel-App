package com.sunasterisk.travelapp.ui.home.restaurant

import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseFragment

class RestaurantTabFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_restaurant_tab

    override fun initComponents() = Unit

    companion object {
        const val TITLE = "EAT"
        fun newInstance() = RestaurantTabFragment()
    }
}
