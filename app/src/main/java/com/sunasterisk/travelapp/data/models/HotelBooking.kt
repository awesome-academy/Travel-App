package com.sunasterisk.travelapp.data.models

import android.content.ContentValues
import android.database.Cursor

data class HotelBooking(
    var id: Int = -1,
    val hotel: Hotel,
    val email: String,
    val phone: String,
    val name: String,
    var checkinDate: String,
    var checkoutDate: String,
    var numAdult: Int,
    var numChildren: Int
) {
    constructor(cursor: Cursor) : this(
        cursor.getInt(cursor.getColumnIndex(ID)),
        Hotel(id = cursor.getString(cursor.getColumnIndex(HOTEL_ID))),
        cursor.getString(cursor.getColumnIndex(User.NAME)),
        cursor.getString(cursor.getColumnIndex(User.EMAIL)),
        cursor.getString(cursor.getColumnIndex(User.PHONE)),
        cursor.getString(cursor.getColumnIndex(IN_DATE)),
        cursor.getString(cursor.getColumnIndex(OUT_DATE)),
        cursor.getInt(cursor.getColumnIndex(NUM_ADULT)),
        cursor.getInt(cursor.getColumnIndex(NUM_CHILDREN))
    )

    fun getValues(): ContentValues =
        ContentValues().apply {
            put(HOTEL_ID, hotel.id)
            put(User.EMAIL, email)
            put(User.NAME, name)
            put(User.PHONE, phone)
            put(IN_DATE, checkinDate)
            put(OUT_DATE, checkoutDate)
            put(NUM_ADULT, numAdult)
            put(NUM_CHILDREN, numChildren)
        }

    companion object {
        const val TABLE_NAME = "booking"
        const val ID = "booking_id"
        const val HOTEL_ID = "hotel_id"
        const val IN_DATE = "checkin_date"
        const val OUT_DATE = "checkout_date"
        const val NUM_ADULT = "number_adult"
        const val NUM_CHILDREN = "number_children"
    }
}
