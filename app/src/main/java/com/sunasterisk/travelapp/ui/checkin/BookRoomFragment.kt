package com.sunasterisk.travelapp.ui.checkin

import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseFragment

class BookRoomFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_book_room

    override fun initComponents() = Unit

    companion object {
        const val TITLE = "Select Room"
    }
}
