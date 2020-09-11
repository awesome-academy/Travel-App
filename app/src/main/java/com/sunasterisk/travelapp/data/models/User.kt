package com.sunasterisk.travelapp.data.models

import android.content.ContentValues
import android.database.Cursor

data class User(
    var id: Int = -1,
    val name: String,
    val email: String,
    var password: String,
    var phone: String
) {

    constructor(cursor: Cursor) : this(
        cursor.getInt(cursor.getColumnIndex(ID)),
        cursor.getString(cursor.getColumnIndex(NAME)),
        cursor.getString(cursor.getColumnIndex(EMAIL)),
        cursor.getString(cursor.getColumnIndex(PASSWORD)),
        cursor.getString(cursor.getColumnIndex(PHONE))
    )

    fun getValues(): ContentValues =
        ContentValues().apply {
            put(EMAIL, email)
            put(PASSWORD, password)
            put(NAME, name)
            put(PHONE, phone)
        }

    companion object {
        const val TABLE_NAME = "user"
        const val ID = "user_Id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val PHONE = "phone"
    }
}
