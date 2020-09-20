package com.sunasterisk.travelapp.ui.home.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.custom.MaterialPickDialogFragment
import kotlinx.android.synthetic.main.fragment_pick_traveler.*

class TravelersPickDialogFragment : MaterialPickDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pick_traveler, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        peopleNumberPick.setDialogFragment(this)
    }

    fun setPickView(picker: TextView) {
        peopleNumberPick.setPicker(picker)
    }
}
