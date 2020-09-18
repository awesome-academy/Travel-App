package com.sunasterisk.travelapp.ui.list.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.custom.MaterialPickDialogFragment

class FilterDialogFragment : MaterialPickDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_filtter_restaurant, container, false)
}
