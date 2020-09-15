package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.source.BookingDataSource

class BookingRepositoryImpl private constructor(
    private val local: BookingDataSource.Local
) : BookingRepository {
    override fun insertBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>) {
        local.insertBooking(booking, callback)
    }

    override fun getBookings(callback: OnDataCallback<List<HotelBooking>>) {
        local.getBookings(callback)
    }

    override fun deleteBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>) {
        local.deleteBooking(booking, callback)
    }

    override fun updateBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>) {
        local.updateBooking(booking, callback)
    }

    companion object {
        @Volatile
        private var INSTANCE: BookingRepository? = null

        fun getInstance(
            local: BookingDataSource.Local
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: BookingRepositoryImpl(local).also { INSTANCE = it }
        }
    }
}
