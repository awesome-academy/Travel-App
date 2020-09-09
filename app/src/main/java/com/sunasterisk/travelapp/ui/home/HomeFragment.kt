package com.sunasterisk.travelapp.ui.home

import androidx.viewpager.widget.ViewPager
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.adapter.HomeTabsAdapter
import com.sunasterisk.travelapp.ui.base.BaseFragment
import com.sunasterisk.travelapp.ui.home.hotel.HotelTabFragment
import com.sunasterisk.travelapp.ui.home.restaurant.RestaurantTabFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_home

    private lateinit var tapsCallback: OnTabsSetupListener

    override fun initComponents() {
        setTabsWithViewPager()
    }

    fun setTabsCallbackListener(callback: OnTabsSetupListener) {
        this.tapsCallback = callback
    }

    private fun setTabsWithViewPager() {
        setupAdapter()
        tapsCallback.onSetup(viewpager)
    }

    private fun setupAdapter() {
        childFragmentManager?.let {
            HomeTabsAdapter(it)
        }.apply {
            addFragment(HotelTabFragment.newInstance(), HotelTabFragment.TITLE)
            addFragment(RestaurantTabFragment.newInstance(), RestaurantTabFragment.TITLE)
            viewpager.adapter = this
        }
    }

    interface OnTabsSetupListener {
        fun onSetup(viewPager: ViewPager)
    }
    
    companion object {
        fun newInstance() = HomeFragment()
    }
}
