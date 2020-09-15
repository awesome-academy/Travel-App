package com.sunasterisk.travelapp.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.models.User

class DatabaseHelper private constructor(context: Context, dbName: String, version: Int) :
    SQLiteOpenHelper(context, dbName, null, version) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase?.run {
            execSQL(SQLITE_CREATE_TABLE_LOCATION)
            execSQL(SQLITE_CREATE_TABLE_USER)
            execSQL(SQLITE_CREATE_TABLE_BOOKING)
            execSQL(SQLITE_CREATE_TABLE_RESERVATION)
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, old: Int, new: Int) {
        sqLiteDatabase?.run {
            execSQL(SQLITE_DELETE_TABLE_LOCATION)
            execSQL(SQLITE_DELETE_TABLE_USER)
            execSQL(SQLITE_DELETE_TABLE_BOOKING)
            execSQL(SQLITE_DELETE_TABLE_RESERVATION)
        }
    }

    companion object {
        private const val DB_NAME = "traveldb"
        private const val DB_VERSION = 1
        private const val COMA_SEP = ","

        private const val SQLITE_CREATE_TABLE_LOCATION =
            "CREATE TABLE " + Location.TABLE_NAME + " (" + Location.ID +
                    " TEXT PRIMARY KEY " + COMA_SEP +
                    User.ID + " INTEGER" + COMA_SEP +
                    Location.TYPE + " TEXT" + COMA_SEP +
                    Location.LOCATION_NAME + " TEXT" + COMA_SEP +
                    Location.LOCATION + " TEXT" + COMA_SEP +
                    Location.THUMB + " TEXT" + COMA_SEP +
                    Location.LARGE + " TEXT" + COMA_SEP +
                    Location.PROPERTIES_NUMBER + " INTEGER" + COMA_SEP +
                    Location.DES + " TEXT" +
                    ")"

        private const val SQLITE_CREATE_TABLE_USER =
            "CREATE TABLE " + User.TABLE_NAME + " (" + User.ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT" + COMA_SEP +
                    User.NAME + " TEXT" + COMA_SEP +
                    User.EMAIL + " TEXT" + COMA_SEP +
                    User.PASSWORD + " TEXT" + COMA_SEP +
                    User.PHONE + " TEXT" +
                    ")"

        private const val SQLITE_CREATE_TABLE_BOOKING =
            "CREATE TABLE " + HotelBooking.TABLE_NAME + " (" + HotelBooking.ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT" + COMA_SEP +
                    HotelBooking.HOTEL_ID + " TEXT" + COMA_SEP +
                    User.NAME + " TEXT" + COMA_SEP +
                    User.EMAIL + " TEXT" + COMA_SEP +
                    User.PHONE + " TEXT" + COMA_SEP +
                    HotelBooking.IN_DATE + " TEXT" + COMA_SEP +
                    HotelBooking.OUT_DATE + " TEXT" + COMA_SEP +
                    HotelBooking.NUM_ADULT + " INTEGER" + COMA_SEP +
                    HotelBooking.NUM_CHILDREN + " INTEGER" +
                    ")"

        private const val SQLITE_CREATE_TABLE_RESERVATION =
            "CREATE TABLE " + Reservation.TABLE_NAME + " (" + Reservation.ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT" + COMA_SEP +
                    Reservation.RESTAURANT_ID + " TEXT" + COMA_SEP +
                    User.NAME + " TEXT" + COMA_SEP +
                    User.EMAIL + " TEXT" + COMA_SEP +
                    User.PHONE + " TEXT" + COMA_SEP +
                    Reservation.ARRIVAL_TIME + " TEXT" + COMA_SEP +
                    Reservation.NUM_ADULT + " INTEGER" + COMA_SEP +
                    Reservation.NUM_CHILDREN + " INTEGER" +
                    ")"

        private const val SQLITE_DELETE_TABLE_LOCATION =
            "DROP TABLE IF EXISTS ${Location.TABLE_NAME}"

        private const val SQLITE_DELETE_TABLE_USER =
            "DROP TABLE IF EXISTS ${User.TABLE_NAME}"

        private const val SQLITE_DELETE_TABLE_BOOKING =
            "DROP TABLE IF EXISTS ${HotelBooking.TABLE_NAME}"

        private const val SQLITE_DELETE_TABLE_RESERVATION =
            "DROP TABLE IF EXISTS ${HotelBooking.TABLE_NAME}"

        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseHelper(context, DB_NAME, DB_VERSION).also { INSTANCE = it }
            }
    }
}
