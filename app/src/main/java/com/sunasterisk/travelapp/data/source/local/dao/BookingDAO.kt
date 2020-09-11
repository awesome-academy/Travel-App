package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.HotelBooking

interface BookingDAO {
    fun insertBooking(booking: HotelBooking): Boolean
    fun getBookings(): List<HotelBooking>
    fun updateBooking(booking: HotelBooking): Boolean
    fun deleteBooking(booking: HotelBooking): Boolean
}
