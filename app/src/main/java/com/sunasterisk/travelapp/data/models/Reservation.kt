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
) {
    companion object {
        const val TABLE_NAME = "reservation"
        const val ID = "Reservation_Id"
        const val RESTAURANT_ID = "Restaurant_Id"
        const val ARRIVAL_TIME = "Arrival_Time"
        const val NUM_ADULT = "Number_Adult"
        const val NUM_CHILDRENT = "Number_Childrent"
    }
}
