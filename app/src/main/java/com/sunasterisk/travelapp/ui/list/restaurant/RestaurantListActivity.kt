package com.sunasterisk.travelapp.ui.list.restaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.adapter.RestaurantListAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPActivity
import com.sunasterisk.travelapp.ui.detail.restaurant.RestaurantDetailActivity
import com.sunasterisk.travelapp.ui.list.restaurant.RestaurantListContract.View
import com.sunasterisk.travelapp.ui.list.restaurant.RestaurantListContract.Presenter
import com.sunasterisk.travelapp.ui.list.restaurant.FilterDialogFragment.OnDataChange
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import kotlinx.android.synthetic.main.include_restaurant_list_front.*

class RestaurantListActivity :
    BaseMVPActivity<View, Presenter>(),
    View,
    OnDataChange {

    override val presenter by lazy {
        RestaurantListPresenter(Injector.getRestaurantRepository(this))
    }
    override val layoutResource get() = R.layout.activity_restaurant_list

    private val adapter = RestaurantListAdapter {
        onItemClick(it)
    }
    private var sortByPrice = true
    private var maxPrice = 500
    private var minRating = 3

    override fun initComponents() {
        setupToolbar()
        setupRestaurantList()
        setContent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_filter, menu)
        val searchView =
            menu?.findItem(R.id.search_item)?.actionView as? SearchView?
        searchView?.setBackgroundResource(android.R.color.white)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.searchRestaurant(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter) showDialog()
        return super.onOptionsItemSelected(item)
    }

    override fun updateRestaurants(list: List<Restaurant>) {
        adapter.apply {
            updateData(list)
            filter(sortByPrice, maxPrice, minRating)
        }
        dismissProgressDialog()
    }

    override fun sendData(sortByPrice: Boolean, maxPrice: Int, minRating: Int) {
        this.sortByPrice = sortByPrice
        this.maxPrice = maxPrice
        this.minRating = minRating
        adapter.filter(sortByPrice, maxPrice, minRating)
    }

    private fun showDialog() {
        FilterDialogFragment
            .newInstance(sortByPrice, maxPrice, minRating)
            .show(supportFragmentManager, FilterDialogFragment::class.simpleName)
    }

    private fun setupRestaurantList() {
        recyclerViewRestaurant.adapter = adapter
    }

    private fun setContent() {
        val bundle = intent.getBundleExtra(BUNDLE_RESTAURANT)
        bundle?.apply {
            val meal = getString(ARGUMENT_MEAL)
            val restaurant = getString(ARGUMENT_RESTAURANT)
            val longtitude = getString(ARGUMENT_LONGTITUDE) ?: ""
            val lattitude = getString(ARGUMENT_LATITUDE) ?: ""
            val name = getString(ARGUMENT_NAME)
            title = name
            textViewTitleRestaurant.text = resources.getString(R.string.title_restaurant_near, name)
            presenter.getRestaurants(lattitude, longtitude, meal, restaurant)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarListRestaurant)
        toolbarListRestaurant.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun onItemClick(item: Restaurant) {
        val intent = RestaurantDetailActivity.getIntent(this, item)
        startActivity(intent)
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
