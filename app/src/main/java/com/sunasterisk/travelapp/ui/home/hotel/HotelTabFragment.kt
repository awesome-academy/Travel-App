package com.sunasterisk.travelapp.ui.home.hotel

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.DestinationFilter
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.adapter.HotelDestinationsAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPFragment
import com.sunasterisk.travelapp.ui.list.hotel.HotelListActivity
import com.sunasterisk.travelapp.utils.location
import com.sunasterisk.travelapp.utils.screenHeight
import kotlinx.android.synthetic.main.fragment_hotel_tab.*
import kotlinx.android.synthetic.main.fragment_hotel_tab.layoutTabFront
import kotlinx.android.synthetic.main.include_hotel_tab_front.*
import java.util.*

class HotelTabFragment : BaseMVPFragment<HotelContract.View, HotelContract.Presenter>(),
    HotelContract.View,
    View.OnClickListener {

    override val layoutResource get() = R.layout.fragment_hotel_tab

    override val presenter: HotelContract.Presenter by lazy {
        HotelPresenter(Injector.getLocationRepository(requireContext()))
    }

    private val adapter = HotelDestinationsAdapter { item -> onClick(item) }
    private val dialog = TravelersPickDialogFragment()

    override fun initComponents() {
        configureFrontLayout()
        initViews()
        initDestinationList()
    }

    private fun initDestinationList() {
        recyclerViewDestination.adapter = adapter
    }

    private fun initViews() {
        textViewFilterDates.setOnClickListener(this)
        textViewFilterTravelers.setOnClickListener(this)
        editTextFilterLocation.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchLocations(editTextFilterLocation.text.toString())
            }
            true
        }
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
            DestinationFilter.dateRange = picker.headerText
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

    private fun onClick(item: Location) {
        context?.apply {
            val intent = getTabNameIntent(this, item)
            startActivity(intent)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewFilterDates -> setupDatePickerBuilder()
            R.id.textViewFilterTravelers -> {
                fragmentManager?.let { manager ->
                    dialog.show(
                        manager,
                        TravelersPickDialogFragment::class.simpleName
                    )
                }
                textViewFilterTravelers.post {
                    dialog.setPickView(textViewFilterTravelers)
                }
            }
        }
    }

    override fun updateLocations(list: List<Location>) {
        adapter.updateData(list)
        dismissProgressDialog()
    }

    companion object {
        const val TITLE = "Sleep"
        const val EXTRA_PERSON_TAB = "EXTRA_PERSON_TAB"
        const val EXTRA_DATE_RANGE_TAB = "EXTRA_DATE_RANGE_TAB"
        const val EXTRA_LOCATION_TAB = "EXTRA_LOCATION_TAB"

        fun newInstance() = HotelTabFragment()
        fun getTabNameIntent(context: Context, location: Location) =
            Intent(context, HotelListActivity::class.java).apply {
                putExtra(
                    EXTRA_PERSON_TAB,
                    context.resources.getQuantityString(
                        R.plurals.title_filter_travelers,
                        context.resources.getInteger(R.integer.integer_1),
                        DestinationFilter.adultCount, DestinationFilter.childrenCount
                    )
                )
                putExtra(EXTRA_DATE_RANGE_TAB, DestinationFilter.dateRange)
                putExtra(EXTRA_LOCATION_TAB, location.name)
            }
    }

    override fun onError(message: String?) {
        super.onError(message)
        dismissProgressDialog()
    }
}
