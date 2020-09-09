package com.sunasterisk.travelapp.data.models

data class User(
    var id: Int = -1,
    val name: String,
    val email: String,
    var password: String,
    var phone: String
) {
    companion object {
        const val TABLE_NAME = "user"
        const val ID = "User_Id"
        const val NAME = "Name"
        const val EMAIL = "Email"
        const val PASSWORD = "Password"
        const val PHONE = "Phone"
    }
}
