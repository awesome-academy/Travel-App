package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.models.HotelBooking

interface BookingDataSource {
    interface Local {
        fun insertBooking(booking: HotelBooking): Boolean
        fun getBookings(): List<HotelBooking>
        fun deleteBooking(booking: HotelBooking): Boolean
    }
}
