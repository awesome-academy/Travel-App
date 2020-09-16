package com.sunasterisk.travelapp.ui.list.hotel

import android.view.MenuItem
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.adapter.HotelAdapter
import com.sunasterisk.travelapp.ui.base.BaseActivity
import com.sunasterisk.travelapp.ui.custom.TabName
import com.sunasterisk.travelapp.ui.home.hotel.HotelTabFragment.Companion.EXTRA_DATE_RANGE_TAB
import com.sunasterisk.travelapp.ui.home.hotel.HotelTabFragment.Companion.EXTRA_LOCATION_TAB
import com.sunasterisk.travelapp.ui.home.hotel.HotelTabFragment.Companion.EXTRA_PERSON_TAB
import com.sunasterisk.travelapp.utils.location
import com.sunasterisk.travelapp.utils.screenHeight
import kotlinx.android.synthetic.main.activity_hotel_list.*
import kotlinx.android.synthetic.main.include_hotel_list_front.*

class HotelListActivity : BaseActivity() {

    override val layoutResource = R.layout.activity_hotel_list
    private val adapter: HotelAdapter = HotelAdapter()

    override fun initComponents() {
        setupToolbar()
        configureBackdropTab(getTabNameFromIntent())
        initHotelList()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun initHotelList() {
        recyclerViewHotel.adapter = adapter
    }

    private fun getTabNameFromIntent(): TabName {
        val firstTab = intent.getStringExtra(EXTRA_PERSON_TAB) ?: ""
        val secondTab = intent.getStringExtra(EXTRA_DATE_RANGE_TAB) ?: ""
        val thirdTab = intent.getStringExtra(EXTRA_LOCATION_TAB) ?: ""
        return TabName(firstTab, secondTab, thirdTab)
    }

    private fun configureBackdropTab(tabName: TabName) {
        viewDividerHeaderListScreen.post {
            backdropTabLayout.apply {
                setView(constraintLayoutListHotelInclude)
                setTranslateY(screenHeight() - viewDividerHeaderListScreen.location().y.toFloat())
                updateTabsName(tabName)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
