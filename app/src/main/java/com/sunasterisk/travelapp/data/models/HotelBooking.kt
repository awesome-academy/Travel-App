package com.sunasterisk.travelapp.data.models

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
)
