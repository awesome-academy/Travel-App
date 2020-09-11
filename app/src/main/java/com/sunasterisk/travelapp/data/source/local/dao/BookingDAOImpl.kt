package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.source.local.database.DatabaseHelper

class BookingDAOImpl private constructor(databaseHelper: DatabaseHelper) : BookingDAO {
    private val database = databaseHelper.writableDatabase

    override fun insertBooking(booking: HotelBooking): Boolean {
        if (isExist(booking)) return false
        return database.insert(HotelBooking.TABLE_NAME, null, booking.getValues()) > 0
    }

    override fun getBookings(): List<HotelBooking> {
        val cursor = database.query(HotelBooking.TABLE_NAME, null, null, null, null, null, null)
        val bookings = mutableListOf<HotelBooking>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                bookings.add(HotelBooking(cursor))
                cursor.moveToNext()
            }
            cursor.close()
        }
        return bookings
    }

    override fun updateBooking(booking: HotelBooking): Boolean {
        if (!isExist(booking)) return false
        return database.update(
            HotelBooking.TABLE_NAME,
            booking.getValues(),
            "${HotelBooking.ID} =?",
            arrayOf(booking.id.toString())
        ) > 0
    }

    override fun deleteBooking(booking: HotelBooking): Boolean =
        database.delete(
            HotelBooking.TABLE_NAME,
            "${HotelBooking.ID} = ?",
            arrayOf(booking.id.toString())
        ) > 0

    private fun isExist(booking: HotelBooking): Boolean {
        val cursor = database.query(
            HotelBooking.TABLE_NAME,
            null,
            " ${HotelBooking.ID}=?",
            arrayOf(booking.id.toString()),
            null,
            null,
            null
        )
        val result = cursor.moveToFirst()
        cursor.close()
        return result
    }

    companion object {
        @Volatile
        private var INSTANCE: BookingDAO? = null

        fun getInstance(database: DatabaseHelper): BookingDAO =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: BookingDAOImpl(database).also { INSTANCE = it }
            }
    }
}
