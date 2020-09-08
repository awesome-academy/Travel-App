package com.sunasterisk.travelapp.data.models

class Reservation(
    val id: Int = -1,
    val restaurant: Restaurant,
    val email: String,
    val phone: String,
    val name: String,
    var arrivalTime: String,
    var numAdult: Int,
    var numChildrent: Int
)
