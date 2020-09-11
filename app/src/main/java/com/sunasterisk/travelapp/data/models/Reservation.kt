package com.sunasterisk.travelapp.data.models

import android.content.ContentValues
import android.database.Cursor

data class Reservation(
    val id: Int = -1,
    val restaurant: Restaurant,
    val email: String,
    val phone: String,
    val name: String,
    var arrivalTime: String,
    var numAdult: Int,
    var numChildren: Int
) {
    constructor(cursor: Cursor) : this(
        cursor.getInt(cursor.getColumnIndex(ID)),
        Restaurant(id = cursor.getString(cursor.getColumnIndex(RESTAURANT_ID))),
        cursor.getString(cursor.getColumnIndex(User.NAME)),
        cursor.getString(cursor.getColumnIndex(User.EMAIL)),
        cursor.getString(cursor.getColumnIndex(User.PHONE)),
        cursor.getString(cursor.getColumnIndex(ARRIVAL_TIME)),
        cursor.getInt(cursor.getColumnIndex(NUM_ADULT)),
        cursor.getInt(cursor.getColumnIndex(NUM_CHILDREN))
    )

    fun getValues(): ContentValues =
        ContentValues().apply {
            put(RESTAURANT_ID, restaurant.id)
            put(User.EMAIL, email)
            put(User.NAME, name)
            put(User.PHONE, phone)
            put(ARRIVAL_TIME, arrivalTime)
            put(NUM_ADULT, numAdult)
            put(NUM_CHILDREN, numChildren)
        }

    companion object {
        const val TABLE_NAME = "reservation"
        const val ID = "reservation_Id"
        const val RESTAURANT_ID = "restaurant_Id"
        const val ARRIVAL_TIME = "arrival_Time"
        const val NUM_ADULT = "number_Adult"
        const val NUM_CHILDREN = "number_Children"
    }
}
