package com.sunasterisk.travelapp.ui.checkin

import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseFragment

class ConfirmFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_traveler_infor

    override fun initComponents() = Unit

    companion object {
        const val TITLE = "Please Confirm"
    }
}
