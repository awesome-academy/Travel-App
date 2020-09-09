package com.sunasterisk.travelapp.ui.home.hotel
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseFragment

class HotelTabFragment private constructor(): BaseFragment() {

    override val layoutResource get() = R.layout.fragment_hotel_tab

    override fun initComponents() = Unit

    companion object {
        const val TITLE = "Sleep"
        @JvmStatic
        fun newInstance() = HotelTabFragment()
    }
}
