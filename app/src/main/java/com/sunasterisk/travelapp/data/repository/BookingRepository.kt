package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.HotelBooking

interface BookingRepository {

    fun insertBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>)

    fun getBookings(callback: OnDataCallback<List<HotelBooking>>)

    fun deleteBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>)

    fun updateBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>)
}
