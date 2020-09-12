package com.sunasterisk.travelapp.ui.home.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.custom.MaterialPickDialogFragment

class TravelersPickDialogFragment : MaterialPickDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_travelers_pick_dialog, container, false)

}
