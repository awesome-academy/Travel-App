package com.sunasterisk.travelapp.ui.list.hotel

import android.view.MenuItem
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.adapter.HotelAdapter
import com.sunasterisk.travelapp.ui.base.BaseActivity
import com.sunasterisk.travelapp.ui.custom.TabName
import com.sunasterisk.travelapp.utils.location
import com.sunasterisk.travelapp.utils.screenHeight
import kotlinx.android.synthetic.main.activity_hotel_list.*
import kotlinx.android.synthetic.main.include_hotel_list_front.*

class HotelListActivity : BaseActivity() {

    private val adapter: HotelAdapter = HotelAdapter()

    override val layoutResource = R.layout.activity_hotel_list

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
        val firstTab = intent.getStringExtra(TabName.PERSON_TAB_KEY) ?: ""
        val secondTab = intent.getStringExtra(TabName.DATE_RANGE_TAB_KEY) ?: ""
        val thirdTab = intent.getStringExtra(TabName.LOCATION_TAB_KEY) ?: ""
        return TabName(firstTab, secondTab, thirdTab)
    }

    private fun configureBackdropTab(tabName: TabName) {
        viewDividerHeaderListScreen.post {
            backdropTabLayout.apply {
                setView(constraintLayoutListHotelInclude)
                setTranslateY(screenHeight() - viewDividerHeaderListScreen.location().y)
                updateTabsName(tabName)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
