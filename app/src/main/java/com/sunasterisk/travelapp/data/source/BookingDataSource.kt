package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.HotelBooking

interface BookingDataSource {
    interface Local {
        fun insertBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>)
        fun getBookings(callback: OnDataCallback<List<HotelBooking>>)
        fun deleteBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>)
        fun updateBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>)
    }
}
