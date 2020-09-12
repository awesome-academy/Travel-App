package com.sunasterisk.travelapp.ui.home.hotel

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.adapter.HotelDestinationsAdapter
import com.sunasterisk.travelapp.ui.base.BaseFragment
import com.sunasterisk.travelapp.utils.location
import com.sunasterisk.travelapp.utils.screenHeight
import kotlinx.android.synthetic.main.fragment_hotel_tab.*
import kotlinx.android.synthetic.main.include_hotel_tab_front.*
import java.util.*

class HotelTabFragment private constructor() : BaseFragment(), View.OnClickListener {

    override val layoutResource get() = R.layout.fragment_hotel_tab

    override fun initComponents() {
        configureFrontLayout()
        initViews()
        setupDestinationList()
    }

    private fun setupDestinationList() {
        HotelDestinationsAdapter().apply {
            recyclerViewDestination.adapter = this
        }
    }

    private fun initViews() {
        textViewFilterDates.setOnClickListener(this)
        textViewFilterTravelers.setOnClickListener(this)
    }

    private fun setupDatePickerBuilder() {
        val builder = dateRangePicker()
        val now = Calendar.getInstance()
        builder.setTheme(R.style.ThemeOverlay_Catalog_MaterialCalendar_Custom)
        builder.setSelection(androidx.core.util.Pair(now.timeInMillis, now.timeInMillis))
        val picker = builder.build()
        activity?.supportFragmentManager?.let {
            picker.show(it, picker.toString())
        }
        picker.addOnPositiveButtonClickListener {
            textViewFilterDates.text = picker.headerText
        }
    }

    private fun configureFrontLayout() {
        val screenHeight = context?.screenHeight()
        cardViewFilterLocation.viewTreeObserver.addOnGlobalLayoutListener {
            screenHeight?.let {
                BottomSheetBehavior.from(layoutTabFront).peekHeight =
                    (it - cardViewFilterLocation.location().y - cardViewFilterLocation.height - resources.getDimension(
                        R.dimen.dp_16
                    )).toInt()
            }
        }
    }

    companion object {
        const val TITLE = "Sleep"
        fun newInstance() = HotelTabFragment()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewFilterDates -> setupDatePickerBuilder()
            R.id.textViewFilterTravelers -> {
                val dialog = TravelersPickDialogFragment()
                fragmentManager?.let { manager ->
                    dialog.show(
                        manager,
                        TravelersPickDialogFragment::class.simpleName
                    )
                }
            }
        }
    }
}
