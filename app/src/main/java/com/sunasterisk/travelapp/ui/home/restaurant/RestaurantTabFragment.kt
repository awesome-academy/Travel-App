package com.sunasterisk.travelapp.ui.home.restaurant

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.MealType
import com.sunasterisk.travelapp.data.models.RestaurantType
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.adapter.RestaurantTabAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPFragment
import com.sunasterisk.travelapp.ui.list.restaurant.RestaurantListActivity
import com.sunasterisk.travelapp.utils.location
import com.sunasterisk.travelapp.utils.screenHeight
import kotlinx.android.synthetic.main.fragment_hotel_tab.layoutTabFront
import kotlinx.android.synthetic.main.fragment_restaurant_tab.*
import kotlinx.android.synthetic.main.include_hotel_tab_front.*

class RestaurantTabFragment :
    BaseMVPFragment<RestaurantContract.View, RestaurantContract.Presenter>(),
    RestaurantContract.View,
    View.OnClickListener {

    override val presenter: RestaurantContract.Presenter by lazy {
        RestaurantPresenter(Injector.getLocationRepository(requireContext()))
    }

    override val layoutResource get() = R.layout.fragment_restaurant_tab

    private val adapter = RestaurantTabAdapter {
        onItemClick(it)
    }

    private var mealTypesDropdownView: ListPopupWindow? = null
    private var restaurantTypesDropdownView: ListPopupWindow? = null
    private var mealParam: String? = null
    private var restaurantParam: String? = null

    override fun initComponents() {
        configureFrontLayout()
        initViews()
        setupDestinationList()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewRestaurantType -> restaurantTypesDropdownView?.show()
            R.id.textViewMealType -> mealTypesDropdownView?.show()
        }
    }

    override fun updateLocations(list: List<Location>) {
        adapter.updateData(list)
    }

    private fun setupDestinationList() {
        recyclerViewDestination.adapter = adapter
    }

    private fun initViews() {
        createMealTypePopup()
        createRestaurantTypePopup()
        textViewMealType.setOnClickListener(this)
        textViewRestaurantType.setOnClickListener(this)
        editTextLocation.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchLocations(editTextLocation.text.toString())
            }
            true
        }
    }

    private fun createMealTypePopup() {
        val mealTypes = MealType.values()
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                mealTypes
            )
        }
        mealTypesDropdownView = ListPopupWindow(requireContext())
        mealTypesDropdownView?.apply {
            setAdapter(adapter)
            anchorView = textViewMealType
            isModal = true
            textViewMealType.post {
                width = textViewMealType.measuredWidth
            }
            setOnItemClickListener { _, _, position, _ ->
                textViewMealType.text = mealTypes[position].toString()
                mealTypesDropdownView?.dismiss()
                mealParam =
                    mealTypes.getOrNull(position)
                        ?.takeUnless { it == MealType.All }
                        ?.value
            }
        }
    }

    private fun createRestaurantTypePopup() {
        val restaurantTypes = RestaurantType.values()
        restaurantTypesDropdownView = ListPopupWindow(requireContext())
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                restaurantTypes
            )
        }
        restaurantTypesDropdownView?.apply {
            setAdapter(adapter)
            anchorView = textViewRestaurantType
            isModal = true
            textViewRestaurantType.post {
                width = textViewRestaurantType.measuredWidth
            }
            setOnItemClickListener { _, _, position, _ ->
                textViewRestaurantType.text = restaurantTypes[position].toString()
                restaurantTypesDropdownView?.dismiss()
                restaurantParam =
                    restaurantTypes
                        .getOrNull(position)
                        ?.takeUnless { it == RestaurantType.All }
                        ?.value
            }
        }
    }

    private fun configureFrontLayout() {
        val screenHeight = context?.screenHeight()
        cardViewLocation.viewTreeObserver.addOnGlobalLayoutListener {
            screenHeight?.let {
                BottomSheetBehavior.from(layoutTabFront).peekHeight =
                    (it - cardViewLocation.location().y - cardViewLocation.height - resources.getDimension(
                        R.dimen.dp_16
                    )).toInt()
            }
        }
    }

    private fun onItemClick(item: Location) {
        val intent = RestaurantListActivity.getRestaurantListIntent(
            requireContext(),
            item,
            mealParam,
            restaurantParam
        )
        startActivity(intent)
    }

    companion object {

        const val TITLE = "Eat"

        fun newInstance() = RestaurantTabFragment()
    }
}
