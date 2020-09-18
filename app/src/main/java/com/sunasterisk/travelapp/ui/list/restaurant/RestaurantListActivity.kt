package com.sunasterisk.travelapp.ui.list.restaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_restaurant_list.*

class RestaurantListActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_restaurant_list

    override fun initComponents() {
        setupToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_filter, menu)
        val searchView =
            menu?.findItem(R.id.search_item)?.actionView as SearchView
        searchView.setBackgroundResource(android.R.color.white)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if(item.itemId == R.id.filter) {
                val dialog = FilterDialogFragment()
                dialog.show(supportFragmentManager, FilterDialogFragment::class.simpleName)
            }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarListRestaurant)
        toolbarListRestaurant.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {

        private const val ARGUMENT_MEAL = "ARGUMENT_MEAL"
        private const val ARGUMENT_RESTAURANT = "ARGUMENT_RESTAURANT"
        private const val ARGUMENT_LATITUDE = "ARGUMENT_LATITUDE"
        private const val ARGUMENT_LONGTITUDE = "ARGUMENT_LONGTITUDE"
        private const val ARGUMENT_NAME = "ARGUMENT_NAME"
        private const val BUNDLE_RESTAURANT = "BUNDLE_RESTAURANT"

        fun getRestaurantListIntent(
            context: Context,
            location: Location,
            mealParam: String? = null,
            restaurantParam: String? = null
        ): Intent {
            val bundle = Bundle()
                .apply {
                    mealParam?.let {
                        putString(ARGUMENT_MEAL, it)
                    }
                    restaurantParam?.let {
                        putString(ARGUMENT_RESTAURANT, it)
                    }
                    putString(ARGUMENT_LATITUDE, location.latitude)
                    putString(ARGUMENT_LONGTITUDE, location.longitude)
                    putString(ARGUMENT_NAME, location.name)
                }
            return Intent(context, RestaurantListActivity::class.java)
                .putExtra(BUNDLE_RESTAURANT, bundle)
        }
    }
}
