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
) {
    companion object {
        const val TABLE_NAME = "booking"
        const val ID = "Booking_Id"
        const val HOTEL_ID = "Hotel_Id"
        const val IN_DATE = "Checkin_Date"
        const val OUT_DATE = "Checkout_Date"
        const val NUM_ADULT = "Number_Adult"
        const val NUM_CHILDRENT = "Number_Childrent"
    }
}
