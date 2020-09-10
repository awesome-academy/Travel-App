package com.sunasterisk.travelapp.ui.home.hotel

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseFragment
import com.sunasterisk.travelapp.utils.location
import kotlinx.android.synthetic.main.fragment_hotel_tab.*

class HotelTabFragment private constructor() : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_hotel_tab

    override fun initComponents() {
        configureFrontLayout()
    }

    private fun configureFrontLayout() {
        val screenHeight = context?.resources?.displayMetrics?.heightPixels
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
}
