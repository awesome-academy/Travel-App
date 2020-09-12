package com.sunasterisk.travelapp.data.source.local

import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.source.BookingDataSource
import com.sunasterisk.travelapp.data.source.local.dao.BookingDAO

class BookingLocalDatasource private constructor(private val bookingDAO: BookingDAO) :
    BookingDataSource.Local {

    override fun insertBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<HotelBooking, Boolean>(callback) {
            bookingDAO.insertBooking(it)
        }.execute(booking)
    }

    override fun getBookings(callback: OnDataCallback<List<HotelBooking>>) {
        LoadDataAsync<Unit, List<HotelBooking>>(callback) {
            bookingDAO.getBookings()
        }.execute(Unit)
    }

    override fun deleteBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<HotelBooking, Boolean>(callback) {
            bookingDAO.deleteBooking(it)
        }.execute(booking)
    }

    override fun updateBooking(booking: HotelBooking, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<HotelBooking, Boolean>(callback) {
            bookingDAO.updateBooking(it)
        }.execute(booking)
    }

    companion object {
        @Volatile
        private var instance: BookingLocalDatasource? = null

        fun getInstance(bookingDAO: BookingDAO) =
            instance ?: synchronized(this) {
                instance ?: BookingLocalDatasource(bookingDAO).also {
                    instance = it
                }
            }
    }
}
